package ru.grak.brt.service.billing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.brt.entity.Client;
import ru.grak.brt.repository.ClientRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final ClientRepository clientRepository;

    public void decreaseBalance(String msisdn, BigDecimal cost) {
        var client = clientRepository.findByPhoneNumber(msisdn);
        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.subtract(cost);
        client.setBalance(updatedBalance);

        clientRepository.save(client);
    }

    //TODO native query
    public void refillBalance(Client client, BigDecimal deposit) {
        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.add(deposit);
        client.setBalance(updatedBalance);

        clientRepository.save(client);
    }
}
