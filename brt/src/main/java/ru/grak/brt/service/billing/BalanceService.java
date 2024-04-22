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

    public void decreaseBalance(Client client, BigDecimal fee){
        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.subtract(fee);
        client.setBalance(updatedBalance);

        clientRepository.save(client);
    }

    public void refillBalance(Client client, BigDecimal deposit){
        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.add(deposit);
        client.setBalance(updatedBalance);

        clientRepository.save(client);
    }
}
