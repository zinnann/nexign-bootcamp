package ru.grak.crm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import ru.grak.common.enums.TypeTariff;
import ru.grak.crm.dto.AbonentDto;
import ru.grak.crm.entity.Client;
import ru.grak.crm.exceptions.AbonentAlreadyExistException;
import ru.grak.crm.exceptions.ClientNotFoundException;
import ru.grak.crm.exceptions.TariffNotFoundException;
import ru.grak.crm.repository.ClientRepository;
import ru.grak.crm.repository.TariffRepository;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final TariffRepository tariffRepository;
    private final ClientRepository clientRepository;

    @Transactional
    @Modifying
    public void changeTariff(String msisdn, String typeTariff) {
        var client = clientRepository.findById(msisdn)
                .orElseThrow(() ->
                        new ClientNotFoundException("Client with msisdn:{0} not found", msisdn));

        var tariff = tariffRepository.findById(TypeTariff.valueOf(typeTariff))
                .orElseThrow(() ->
                        new TariffNotFoundException("Tariff with id:{0} not found", typeTariff));

        client.setTariff(tariff.getTariff());
        clientRepository.save(client);
    }

    @Transactional
    public Client createAbonent(AbonentDto abonentDto) {
        if (clientRepository.existsByPhoneNumber(abonentDto.getPhoneNumber()))
            throw new AbonentAlreadyExistException("Abonent with number: {0}", abonentDto.getPhoneNumber());

        return clientRepository.save(abonentDto.toEntity());
    }
}
