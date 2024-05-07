package ru.grak.brt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grak.brt.service.automation.AutoUpdatingDataService;
import ru.grak.brt.service.billing.AuthorizationService;
import ru.grak.brt.service.billing.BalanceService;
import ru.grak.brt.service.billing.CdrPlusService;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.common.dto.CostDataDto;
import ru.grak.common.enums.TypeCall;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrtService {

    private final AuthorizationService auth;
    private final CdrPlusService cdrPlusService;
    private final AutoUpdatingDataService autoUpdatingDataService;
    private final BalanceService balanceService;

    private final KafkaTemplate<String, CallDataRecordPlusDto> kafkaTemplate;

    private int currentMonth = 1;

    //TODO transactional + currMonth

    @KafkaListener(topics = "topic1", groupId = "topic-default", containerFactory = "kafkaListenerContainerFactory")
    public void processingAndSendCallData(String data) {

        List<CallDataRecordDto> cdr = parseCallDataFromReceivedData(data);

        for (CallDataRecordDto callDataRecord : cdr) {
            int callMonth = extractMonthFromCallData(callDataRecord);

            if (callMonth > currentMonth) {
                currentMonth = callMonth;
                autoUpdatingDataService.autoChangeBalanceAndTariff();
            }

            if (auth.isAuthorizedMsisdn(callDataRecord.getMsisdnFirst())) {
                CallDataRecordPlusDto cdrPlus = cdrPlusService.createCdrPlus(callDataRecord);
//                kafkaTemplate.send("topic2", cdrPlus);
            }
        }
    }

    @KafkaListener(topics = "topic2-retry", groupId = "topic-default", containerFactory =
            "costDataKafkaListenerContainerFactory")
    public void processingCostData(CostDataDto costData) {
        System.out.println(costData);
//        balanceService.decreaseBalance(costData.getMsisdn(), costData.getCost());
    }

    private List<CallDataRecordDto> parseCallDataFromReceivedData(String data) {

        List<CallDataRecordDto> callDataRecordList = new ArrayList<>();
        var callDataRaws = data.split(System.lineSeparator());

        for (String raw : callDataRaws) {
            var cdr = raw.split(", ");

            callDataRecordList.add(
                    CallDataRecordDto
                            .builder()
                            .typeCall(TypeCall.fromNumericValueOfType(cdr[0]))
                            .msisdnFirst(cdr[1])
                            .msisdnSecond(cdr[2])
                            .dateTimeStartCall(Long.parseLong(cdr[3]))
                            .dateTimeEndCall(Long.parseLong(cdr[4]))
                            .build()
            );
        }

        return callDataRecordList;
    }

    private int extractMonthFromCallData(CallDataRecordDto callDataRecord) {
        var dateTimeStartCall = callDataRecord.getDateTimeStartCall();

        return LocalDate.
                ofInstant(Instant.ofEpochSecond(dateTimeStartCall), ZoneOffset.UTC)
                .getMonthValue();
    }

}
