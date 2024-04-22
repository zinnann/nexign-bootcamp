package ru.grak.brt.service.automation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.grak.brt.entity.Client;
import ru.grak.brt.entity.Tariff;
import ru.grak.brt.entity.Traffic;
import ru.grak.brt.repository.ClientRepository;
import ru.grak.brt.repository.TariffRepository;
import ru.grak.brt.repository.TrafficRepository;
import ru.grak.brt.service.billing.BalanceService;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.enums.TypeTariff;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AutoModifyingDataService {

    private final ClientRepository clientRepository;
    private final TariffRepository tariffRepository;
    private final TrafficRepository trafficRepository;
    private final BalanceService balanceService;

    @Value("${brt.deposit.max}")
    private double maxAutoDeposit;

    @Value("${brt.random.clients.amount}")
    private int maxClientsForUpdatingTariff;

    public void autoChangeBalanceAndTariff(CallDataRecordDto cdr) {
        int currentMonth = extractMonth(cdr.getDateTimeStartCall());
        int previousMonth = extractLastPreviousMonthFromTraffic();

        if (currentMonth > previousMonth) {
            List<Client> clients = clientRepository.findAll();
            changeClientsBalance(clients);
            changeClientsTariff(clients);
        }
    }

    private void changeClientsBalance(List<Client> clients) {
        for (Client client : clients) {
            BigDecimal deposit = BigDecimal.valueOf(
                    ThreadLocalRandom.current().nextDouble(maxAutoDeposit));

            balanceService.refillBalance(client, deposit);
        }
    }

    private void changeClientsTariff(List<Client> clients) {
        int amountClientsForChanging = ThreadLocalRandom.current()
                .nextInt(maxClientsForUpdatingTariff) + 1;

        var tariffs = tariffRepository.findAll();

        for (int i = 0; i < amountClientsForChanging; i++) {
            var client = clients.get(
                    ThreadLocalRandom.current().nextInt(clients.size()));

            var currentTariff = client.getTariff();
            var updatedTariff = getModifiedTariff(tariffs, currentTariff);
            client.setTariff(updatedTariff);

            clientRepository.save(client);
        }
        //+ списание за предыдущий месяц?
    }

    private TypeTariff getModifiedTariff(List<Tariff> tariffs, TypeTariff currentTariff) {
        var updatedTariff = tariffs.get(
                ThreadLocalRandom.current().nextInt(tariffs.size())).getTariff();

        while (updatedTariff.equals(currentTariff)) {
            updatedTariff = tariffs.get(
                    ThreadLocalRandom.current().nextInt(tariffs.size())).getTariff();
        }
        return updatedTariff;
    }

    //TODO overload and allocate + sql
    private int extractLastPreviousMonthFromTraffic() {
        return trafficRepository.findAll()
                .stream()
                .max(Comparator.comparing(Traffic::getMonth)).get()
                .getMonth();
    }

    private int extractMonth(long dateTime) {
        return LocalDate.
                ofInstant(Instant.ofEpochSecond(dateTime), ZoneOffset.UTC)
                .getMonthValue();
    }
}
