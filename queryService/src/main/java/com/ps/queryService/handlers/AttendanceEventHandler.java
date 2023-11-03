package com.ps.queryService.handlers;

import com.ps.queryService.entities.AttendanceEventEntity;
import com.ps.queryService.repository.AttendanceEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;

@Service
public class AttendanceEventHandler {
	private static final Logger logger = LoggerFactory.getLogger(AttendanceEventHandler.class);
	@Autowired
	private AttendanceEventRepository repo;
	
	public Flux<AttendanceEventEntity> getAttendanceEventsByEmployeeId(String employeeId) {
		return repo.findByEmployeeId(employeeId);
	}

	public Flux<AttendanceEventEntity> getAttendanceEventsByDayWise(String employeeId, Date startDate, Date endDate) {
		repo.findByEmployeeIdAndDateAsc(employeeId, startDate, endDate)
				.doOnNext(item -> logger.info("Received item: " + item))
				.subscribe();
		return null;
	}
}
