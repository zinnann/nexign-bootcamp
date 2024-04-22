package ru.grak.brt.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.grak.brt.entity.Client;
import ru.grak.brt.entity.Traffic;
import ru.grak.brt.repository.ClientRepository;
import ru.grak.brt.repository.TariffRepository;
import ru.grak.brt.repository.TrafficRepository;
import ru.grak.common.dto.CallDataRecordDto;

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

    @Value("${brt.max-deposit}")
    private double maxAutoDeposit;

    public void autoChangeBalanceAndTariff(CallDataRecordDto cdr) {

        int currentMonth = extractMonth(cdr.getDateTimeStartCall());
        int previousMonth = extractLastPreviousMonthFromTraffic();

        if (currentMonth > previousMonth) {
            List<Client> clients = clientRepository.findAll();

            for (Client client : clients) {
                BigDecimal deposit = BigDecimal.valueOf(
                        ThreadLocalRandom.current().nextDouble(maxAutoDeposit));
                balanceService.refillBalance(client, deposit);
            }
        }

    }

    //отдельно + overload
    private int extractLastPreviousMonthFromTraffic(){
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
