package ru.grak.cdr.service.generate;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.enums.TypeCall;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CallDataService {

    private final DataGenerator dataGenerator;

    @Value("${cdr.generate.max-call-duration}")
    private int maxCallDuration;

    public CallDataRecordDto generateRandomCallData(long callStartDateTime, Pair<String, String> msisdnPair) {

        TypeCall typeCall = ThreadLocalRandom.current().nextBoolean()
                ? TypeCall.OUTGOING
                : TypeCall.INCOMING;

        String firstMsisdn = msisdnPair.a;
        String secondMsisdn = msisdnPair.b;

        long callEndDateTime = callStartDateTime
                + dataGenerator.generateRandomCallDuration(maxCallDuration);

        return CallDataRecordDto.builder()
                .typeCall(typeCall)
                .msisdnFirst(firstMsisdn)
                .msisdnSecond(secondMsisdn)
                .dateTimeStartCall(callStartDateTime)
                .dateTimeEndCall(callEndDateTime)
                .build();
    }
}
