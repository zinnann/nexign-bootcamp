package ru.grak.brt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.brt.repository.ClientRepository;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.common.enums.TypeTariff;

@Service
@RequiredArgsConstructor
public class CdrPlusService {

    private final ClientRepository clientRepository;

    public CallDataRecordPlusDto createCdrPlus(CallDataRecordDto cdr) {

        TypeTariff tariff = clientRepository
                .findByPhoneNumber(cdr.getMsisdnFirst())
                .getTariff_id();

        return CallDataRecordPlusDto.builder()
                .typeCall(cdr.getTypeCall())
                .msisdnFirst(cdr.getMsisdnFirst())
                .msisdnSecond(cdr.getMsisdnSecond())
                .dateTimeStartCall(cdr.getDateTimeStartCall())
                .dateTimeEndCall(cdr.getDateTimeEndCall())
                .typeTariff(tariff)
                .build();
    }
}
