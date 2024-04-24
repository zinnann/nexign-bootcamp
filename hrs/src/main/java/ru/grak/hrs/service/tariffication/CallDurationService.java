package ru.grak.hrs.service.tariffication;

import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordPlusDto;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
public class CallDurationService {

    public Duration calculateCallDuration(CallDataRecordPlusDto callDataRecordPlusDto) {
        long duration = callDataRecordPlusDto.getDateTimeEndCall()
                - callDataRecordPlusDto.getDateTimeStartCall();

        return Duration.ofSeconds(duration);
    }

    public Duration roundingCallDuration(Duration duration) {

        return duration.truncatedTo(ChronoUnit.MINUTES).plusMinutes(1);
    }
}
