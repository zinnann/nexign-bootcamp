package ru.grak.crm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ru.grak.crm.dto.PaymentDto;
import ru.grak.crm.exceptions.ClientNotFoundException;
import ru.grak.crm.repository.ClientRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SubscriberService {

    private final ClientRepository clientRepository;

    @Transactional
    @Modifying
    public BigDecimal pay(PaymentDto payment) {

        var client = clientRepository.findByPhoneNumber(payment.getMsisdn())
                .orElseThrow(() ->
                        new ClientNotFoundException("Client with msisdn:{0} not found", payment.getMsisdn()));
        System.out.println(client);

        BigDecimal balance = client.getBalance();
        BigDecimal updatedBalance = balance.add(payment.getDeposit());
        client.setBalance(updatedBalance);

        clientRepository.save(client);

        return updatedBalance;
    }
}
