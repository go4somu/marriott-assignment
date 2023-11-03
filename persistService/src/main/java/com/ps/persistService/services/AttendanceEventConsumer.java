package com.ps.persistService.services;

import com.ps.common.models.AttendanceEvent;
import com.ps.persistService.entities.AttendanceEventEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class AttendanceEventConsumer {
	private static final Logger logger = LoggerFactory.getLogger(AttendanceEventConsumer.class);
	private final AttendanceEventService attendanceEventService;

	@Autowired
	public AttendanceEventConsumer(AttendanceEventService attendanceEventService) {
		this.attendanceEventService = attendanceEventService;
	}

	@KafkaListener(topics = "attendance-events", groupId = "consumer-group-persist")
	public Mono<AttendanceEventEntity> consumeAttendanceEvent(AttendanceEvent ae) {
		logger.info(ae.toString());

		AttendanceEventEntity aeEntity =
				new AttendanceEventEntity();
		aeEntity.setEmployeeId(ae.getEmployeeId());
		aeEntity.setEventType(ae.getEventType());
		aeEntity.setTimestamp(ae.getTimestamp());
		aeEntity.setId(ae.getId());

		return attendanceEventService.saveAttendanceEvent(aeEntity);
	}

	
/*
	@KafkaListener(topics = "attendance-events", groupId = "consumer-group-persist")
	public Mono<AttendanceEvent> consumeAttendanceEvent(AttendanceEvent attendanceEvent) {
        System.out.println(attendanceEvent);
        ObjectMapper objectMapper = new ObjectMapper();
        AttendanceEventDTO ae = null;
        try {
			ae = objectMapper.readValue(attendanceEvent, AttendanceEventDTO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        AttendanceEvent aeEntity = new AttendanceEvent();
        aeEntity.setEmployeeId(ae.getEmployeeId());
        aeEntity.setEventType(ae.getEventType());
        aeEntity.setTimestamp(ae.getTimestamp());
        aeEntity.setId(ae.getId());
     
		return attendanceEventService.saveAttendanceEvent(aeEntity);
    }
	
	/*
	@KafkaListener(topics = "first_topic", groupId = "consumer-group-persist")
	public void consumeAttendanceEvent(String val) {
        System.out.println(val);
		//return attendanceEventService.saveAttendanceEvent(attendanceEvent);
    }
    */
}
