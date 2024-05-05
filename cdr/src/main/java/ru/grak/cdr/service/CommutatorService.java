package ru.grak.cdr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grak.cdr.entity.Abonent;
import ru.grak.cdr.service.db.AbonentService;
import ru.grak.cdr.service.db.TransactionService;
import ru.grak.cdr.service.generate.CallDataService;
import ru.grak.cdr.service.generate.DataGenerator;
import ru.grak.cdr.service.generate.FileService;
import ru.grak.common.dto.CallDataRecordDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommutatorService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final CallDataService callDataService;
    private final DataGenerator dataGenerator;
    private final AbonentService abonentService;
    private final FileService fileService;
    private final TransactionService transactionService;

    @Value("${cdr.generate.file.capacity}")
    private int fileCapacity;

    private int recordsAmount;
    private int filesAmount;

    private final long startUnixDateTime = 1704067200;
    private final long endUnixDateTime = 1735689599;

    @EventListener(ApplicationReadyEvent.class)
    public void generateCallDataRecords() throws IOException {

        long currentUnixDateTime = startUnixDateTime;
        List<Abonent> abonents = abonentService.getAbonentsList();
        List<CallDataRecordDto> cdr = new ArrayList<>();

        //отдельно вынести счетчик для получения + ||
        while (currentUnixDateTime <= endUnixDateTime) {

            recordsAmount++;
            var msisdnPair = dataGenerator.getRandomPairOfMsisdn(abonents);

            CallDataRecordDto callDataRecord = callDataService
                    .generateRandomCallData(currentUnixDateTime, msisdnPair);
            cdr.add(callDataRecord);

            if (isPrepared()) {
                filesAmount++;
                fileService.saveCallDataRecords(cdr, filesAmount);
                transactionService.saveTransactions(cdr);

                //для считывания коммутатором данных файла и их отправки
                String fileData = fileService.getCallDataRecords(filesAmount);
                kafkaTemplate.send("topic1", fileData);
                cdr.clear();
            }

            currentUnixDateTime += dataGenerator.generateRandomInterval();
        }
    }

    private boolean isPrepared() {
        return recordsAmount % fileCapacity == 0;
    }

}
