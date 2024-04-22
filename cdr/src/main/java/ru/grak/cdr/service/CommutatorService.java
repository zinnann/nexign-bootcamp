package ru.grak.cdr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordDto;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CommutatorService {

    private final KafkaTemplate<String, CallDataRecordDto> kafkaTemplate;
    private final CallDataGeneratorService recordGeneratorService;

    @Value("${cdr.generate.year}")
    private int year;

    @Value("${cdr.generate.maxCallDuration}")
    private int maxCallDuration;

    @Value("${cdr.generate.maxCalls}")
    private int maxCalls;

    private final static int MONTH = 12;

    @EventListener(ApplicationReadyEvent.class)
    public void generateCallDataRecords(){

        int countCDR = ThreadLocalRandom.current().nextInt(maxCalls);

        for (int month = 1; month <= MONTH; month++) {
            for (int j = 0; j < countCDR; j++) {
                var record = recordGeneratorService.generateRandomCdr(year, month, maxCallDuration);
                kafkaTemplate.send("topic1", record);
            }
        }
    }

}
