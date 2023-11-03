package com.ps.queryService.repository;

import com.ps.queryService.entities.AttendanceEventEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface AttendanceEventRepository extends ReactiveMongoRepository<AttendanceEventEntity, String> {
	Flux<AttendanceEventEntity> findByEmployeeId(String employeeId);

	@Query("{ 'employeeId' : ?0, 'timestamp' : { $gte : ?1 , $lte : ?2 } }")
	Flux<AttendanceEventEntity> findByEmployeeIdAndDateAsc(String employeeId, Date startDate, Date endDate);
}