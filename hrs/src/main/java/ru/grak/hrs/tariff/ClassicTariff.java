package ru.grak.hrs.tariff;

import org.springframework.beans.factory.annotation.Autowired;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.common.enums.TypeCall;
import ru.grak.hrs.entity.Tariff;
import ru.grak.hrs.repository.CallCostRepository;
import ru.grak.hrs.repository.ClientRepository;
import ru.grak.hrs.repository.TariffRepository;
import ru.grak.hrs.service.tariffication.CallDurationService;

import java.math.BigDecimal;

public class ClassicTariff implements BaseTariff {

    @Autowired
    private CallDurationService callDurationService;
    @Autowired
    private TariffRepository tariffRepository;
    @Autowired
    private CallCostRepository callCostRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public BigDecimal calculateCallCost(CallDataRecordPlusDto callDataRecordPlusDto) {

        var duration = callDurationService.calculateCallDuration(callDataRecordPlusDto);
        var roundedDuration = callDurationService.roundingCallDuration(duration);

        TypeCall typeCall = callDataRecordPlusDto.getTypeCall();
        Tariff tariff = tariffRepository.findByTariff(callDataRecordPlusDto.getTypeTariff());
        BigDecimal callCost = BigDecimal.ZERO;

        //в зависисмости от типа звонка и кому звоним внешн или внутр
        if (typeCall.equals(TypeCall.OUTGOING)) {

            var outCallCostType = tariff.getOutCallCostType();
            var callCostType = callCostRepository.findByCallCostType(outCallCostType);

            //второй абонент также клиент нашего оператора
            if (clientRepository.existsByPhoneNumber(callDataRecordPlusDto.getMsisdnSecond())) {
                callCost = callCostType.getInternalCost()
                        .multiply(BigDecimal.valueOf(roundedDuration.toMinutes()));
            } else {
                callCost = callCostType.getExternalCost()
                        .multiply(BigDecimal.valueOf(roundedDuration.toMinutes()));
            }
        }

        return callCost;
    }
}
