package com.ps.publishService.services;

import com.ps.common.models.AttendanceEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {
    private final KafkaTemplate<String, AttendanceEvent> kafkaTemplate;
    private static final String TOPIC = "attendance-events";

    public KafkaMessageService(KafkaTemplate<String, AttendanceEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AttendanceEvent message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
