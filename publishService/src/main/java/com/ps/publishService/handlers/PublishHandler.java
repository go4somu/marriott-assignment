package com.ps.publishService.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.common.models.AttendanceEvent;
import com.ps.publishService.services.KafkaMessageService;

import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
public class PublishHandler {
	private static final Logger logger = LoggerFactory.getLogger(PublishHandler.class);
	@Autowired
	private KafkaMessageService service;
	

    public Mono<AttendanceEvent> handleData(Mono<AttendanceEvent> requestData) {
        return requestData.map(data -> {
        	logger.info("Received :: " + data);
			service.sendMessage(data);
        	return data;
        });
    }

/*	public Mono<String> handleData(Mono<AttendanceEvent> requestData) {
		ObjectMapper objectMapper = new ObjectMapper();
		return requestData.map(data -> {
			System.out.println("Received :: " + data);
			String json = null;
			try {
				json = objectMapper.writeValueAsString(data);
				System.out.println(json);
				service.sendMessage(json);
				return "Inserted :: "+data.getId();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return "";
		});
	}
	*/

}

