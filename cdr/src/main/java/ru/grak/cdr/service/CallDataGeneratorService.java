package ru.grak.cdr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Transaction;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.enums.TypeCall;

import java.util.concurrent.ThreadLocalRandom;

import static ru.grak.cdr.service.DataGeneratorService.generateRandomCallDuration;
import static ru.grak.cdr.service.DataGeneratorService.generateRandomDateTime;

@Service
@RequiredArgsConstructor
public class CallDataGeneratorService {

    private final DataGeneratorService dataGeneratorService;

    public CallDataRecordDto generateRandomCdr(int year, int month, int maxCallDuration) {

        TypeCall typeCall = ThreadLocalRandom.current().nextBoolean()
                ? TypeCall.OUTGOING
                : TypeCall.INCOMING;

        long startDateTime = generateRandomDateTime(year, month);
        long endDateTime = startDateTime + generateRandomCallDuration(maxCallDuration);

        var msisdnPair = dataGeneratorService.getPairRandomMsisdnFromDB();
        String firstMsisdn = msisdnPair.a;
        String secondMsisdn = msisdnPair.b;

        return CallDataRecordDto.builder()
                .typeCall(typeCall)
                .msisdnFirst(firstMsisdn)
                .msisdnSecond(secondMsisdn)
                .dateTimeStartCall(startDateTime)
                .dateTimeEndCall(endDateTime)
                .build();
    }

}
