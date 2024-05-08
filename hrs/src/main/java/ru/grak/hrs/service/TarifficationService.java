package ru.grak.hrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.common.dto.InvoiceDto;
import ru.grak.common.enums.TypeTariff;
import ru.grak.hrs.repository.CallCostRepository;
import ru.grak.hrs.repository.TariffRepository;
import ru.grak.hrs.tariff.TariffFactory;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TarifficationService {

    private final TariffRepository tariffRepository;
    private final CallCostRepository callCostRepository;

    public InvoiceDto createInvoice(CallDataRecordPlusDto cdrPlus,
                                    Map<String, Integer> usedMinutes) {

        TypeTariff tariff = cdrPlus.getTypeTariff();
        var tariffication = TariffFactory.createTariff(
                tariff.getNumericValueOfType(),
                usedMinutes);

        BigDecimal cost = tariffication.calculateCallCost(cdrPlus, tariffRepository, callCostRepository);

        return InvoiceDto.builder()
                .msisdn(cdrPlus.getMsisdnFirst())
                .cost(cost)
                .build();
    }


}
