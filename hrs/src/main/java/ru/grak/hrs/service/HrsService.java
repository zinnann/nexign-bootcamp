package ru.grak.hrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.grak.common.dto.CallDataRecordPlusDto;
import ru.grak.hrs.dto.CallDetailsDto;

@Service
@RequiredArgsConstructor
public class HrsService {

    private final KafkaTemplate<String, CallDetailsDto> kafkaTemplate;

    @KafkaListener(topics = "topic2")
    void listener(CallDataRecordPlusDto cdrPlus) {

    }

}
