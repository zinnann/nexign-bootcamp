package ru.grak.hrs.tariff;

import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.hrs.repository.CallCostRepository;
import ru.grak.hrs.repository.TariffRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public interface BaseTariff {

    BigDecimal calculateCallCost(CallDataRecordPlusDto callDataRecordPlusDto,
                                 TariffRepository tariffRepository,
                                 CallCostRepository callCostRepository);

    default long calculateCallDuration(CallDataRecordPlusDto callDataRecordPlusDto) {

        var duration = Duration.ofSeconds(callDataRecordPlusDto.getDateTimeEndCall()
                - callDataRecordPlusDto.getDateTimeStartCall());

        if (duration.toSeconds() % 60 > 0) {
            duration = duration.truncatedTo(ChronoUnit.MINUTES).plusMinutes(1);
        }

        return duration.toMinutes();
    }

}
