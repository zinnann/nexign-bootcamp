package ru.grak.hrs.tariff;

import ru.grak.common.dto.CallDataRecordPlusDto;

import java.math.BigDecimal;

public interface BaseTariff {

    BigDecimal calculateCallCost(CallDataRecordPlusDto callDataRecordPlusDto);

}
