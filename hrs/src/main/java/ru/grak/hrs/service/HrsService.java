package ru.grak.hrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.common.dto.CostDataDto;
import ru.grak.hrs.service.tariffication.TarifficationService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HrsService {

    private final KafkaTemplate<String, CostDataDto> kafkaTemplate;
    private final TarifficationService tarifficationService;

    @KafkaListener(topics = "topic2", groupId = "group-id", containerFactory = "kafkaListenerContainerFactory")
    public void invoicing(CallDataRecordPlusDto cdrPlus) {

        System.out.println(cdrPlus);
//        var callDetails = tarifficationService.createCallDetails(cdrPlus);

        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("topic2-reply", CostDataDto.
                    builder()
                    .cost(BigDecimal.valueOf(100))
                    .msisdn("7987")
                    .build());
        }


    }

}
