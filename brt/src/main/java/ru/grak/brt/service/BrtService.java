package ru.grak.brt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordDto;
import ru.grak.common.dto.CallDataRecordPlusDto;

@Service
@RequiredArgsConstructor
public class BrtService {
    private final AuthorizationService auth;
    private final CdrPlusService cdrPlusService;
    private final SubscriptionsFeeService subscriptionsFeeService;
    private final KafkaTemplate<String, CallDataRecordPlusDto> kafkaTemplate;

    //считываем из t1, отправляем и получаем в t2/t2-reply
    @KafkaListener(topics = "topic1")
    void listener(CallDataRecordDto callDataRecord) {

        if (auth.isAuthorizedRecord(callDataRecord)){

            subscriptionsFeeService.subscriptionsFeeWithdrawal(callDataRecord);
            CallDataRecordPlusDto cdrPlus = cdrPlusService.createCdrPlus(callDataRecord);
            kafkaTemplate.send("topic2", cdrPlus);

        }
    }
}
