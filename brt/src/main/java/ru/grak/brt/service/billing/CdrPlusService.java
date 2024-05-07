package ru.grak.brt.service.billing;

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
    private final AuthorizationService auth;

    /**
     * Создает расширенную запись данных о звонке CDR+
     * на основе базовой записи CDR, дополняя ее данными о тарифе
     * клиента и маркером - звонок внутри сети (клиенту ромашки)
     * или нет. Это необходимо для последующей тарификации в HRS.
     * <p>
     * Данные клиента не кэшируются (findByPhoneNumber),
     * так как могут изменятся периодически и необходимо
     * передавать всегда актуальные данные (тариф).
     *
     * @param cdr Базовая запись данных о звонке CDR.
     * @return Расширенная запись данных о звонке с дополнительной информацией CDR+.
     */
    public CallDataRecordPlusDto createCdrPlus(CallDataRecordDto cdr) {

        TypeTariff tariff = clientRepository
                .findByPhoneNumber(cdr.getMsisdnFirst())
                .getTariff();

        String secondMsisdn = cdr.getMsisdnSecond();
        var isInternalCall = auth.isAuthorizedMsisdn(secondMsisdn);

        return CallDataRecordPlusDto.builder()
                .typeCall(cdr.getTypeCall())
                .msisdnFirst(cdr.getMsisdnFirst())
                .msisdnSecond(cdr.getMsisdnSecond())
                .dateTimeStartCall(cdr.getDateTimeStartCall())
                .dateTimeEndCall(cdr.getDateTimeEndCall())
                .typeTariff(tariff)
                .isInternalCall(isInternalCall)
                .build();
    }
}
