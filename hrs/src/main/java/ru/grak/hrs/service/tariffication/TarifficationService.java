package ru.grak.hrs.service.tariffication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.common.enums.TypeTariff;
import ru.grak.hrs.dto.CallDetailsDto;
import ru.grak.hrs.service.tariff.TariffFactory;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TarifficationService {

    private final CallDurationService callDurationService;

    public BigDecimal calculateCallCost(CallDataRecordPlusDto cdrPlus) {

        TypeTariff tariff = cdrPlus.getTypeTariff();

        //создавать через паттерн фабрику и обсчитывать согласно своему тарифу
        var tariffication = TariffFactory.createTariff(tariff.getNumericValueOfType());

        return tariffication.calculateCallCost(cdrPlus);
    }

    public CallDetailsDto createCallDetails(CallDataRecordPlusDto cdrPlus) {

        Duration callDuration = callDurationService.calculateCallDuration(cdrPlus);
        BigDecimal callCost = calculateCallCost(cdrPlus);

        return CallDetailsDto.builder()
                .msisdn(cdrPlus.getMsisdnFirst())
                .duration(callDuration)
                .cost(callCost)
                .build();
    }
}
