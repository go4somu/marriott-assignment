package com.ps.persistService.repository;

import com.ps.persistService.entities.AttendanceEventEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AttendanceEventRepository extends ReactiveMongoRepository<AttendanceEventEntity, String> {
}
