package ru.grak.brt.service.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.brt.entity.Client;
import ru.grak.brt.repository.ClientRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    //TODO native query
    private final ClientRepository clientRepository;

    public void decreaseBalance(String msisdn, BigDecimal cost) {
        var client = clientRepository.findByPhoneNumber(msisdn);
        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.subtract(cost);
        client.setBalance(updatedBalance);

        clientRepository.save(client);
    }

    public void refillBalance(Client client, BigDecimal deposit) {
        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.add(deposit);
        client.setBalance(updatedBalance);

        clientRepository.save(client);
    }
    public void printAllBalances() {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            System.out.println("Баланс абонента с номером " + client.getPhoneNumber() + " составляет: " + client.getBalance());
        }
    }
}
