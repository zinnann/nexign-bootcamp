package ru.grak.cdr.service.generate;

import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Abonent;
import ru.grak.cdr.service.db.AbonentService;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.enums.TypeCall;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CallDataService {

    private final DataGenerator dataGenerator;
    private final AbonentService abonentService;

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

    public Optional<CallDataRecordDto> generateMirrorRecord(CallDataRecordDto record) {

        Abonent secondAbonent = abonentService.findByPhoneNumber(record.getMsisdnSecond());

        if (secondAbonent.isRomashkaClient()) {

            TypeCall mirrorTypeCall = record.getTypeCall().equals(TypeCall.OUTGOING)
                    ? TypeCall.INCOMING
                    : TypeCall.OUTGOING;

            return Optional.of(CallDataRecordDto.builder()
                    .typeCall(mirrorTypeCall)
                    .msisdnFirst(record.getMsisdnSecond())
                    .msisdnSecond(record.getMsisdnFirst())
                    .dateTimeStartCall(record.getDateTimeStartCall())
                    .dateTimeEndCall(record.getDateTimeEndCall())
                    .build());
        }

        return Optional.empty();
    }
}
