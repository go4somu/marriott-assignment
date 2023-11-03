package com.ps.persistService.services;

import com.ps.persistService.repository.AttendanceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.persistService.entities.AttendanceEventEntity;

import reactor.core.publisher.Mono;

@Service
public class AttendanceEventService {
	private final AttendanceEventRepository attendanceEventRepository;

    @Autowired
    public AttendanceEventService(AttendanceEventRepository attendanceEventRepository) {
        this.attendanceEventRepository = attendanceEventRepository;
    }

    public Mono<AttendanceEventEntity> saveAttendanceEvent(AttendanceEventEntity attendanceEvent) {
    	Mono<AttendanceEventEntity> savedEntity = attendanceEventRepository.save(attendanceEvent);
    	savedEntity.subscribe();
    	return savedEntity;
    	
        //attendanceEventRepository.save(attendanceEvent)
        //.subscribe(result -> System.out.println("Entity has been saved: "+ result));
    }
}
