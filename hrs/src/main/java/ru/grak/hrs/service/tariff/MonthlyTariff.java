package ru.grak.hrs.service.tariff;

import ru.grak.common.dto.CallDataRecordPlusDto;

import java.math.BigDecimal;


public class MonthlyTariff implements BaseTariff {

    @Override
    public BigDecimal calculateCallCost(CallDataRecordPlusDto callDataRecordPlusDto) {
        return null;
    }
}
