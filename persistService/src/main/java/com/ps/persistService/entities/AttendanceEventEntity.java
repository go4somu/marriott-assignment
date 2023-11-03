package com.ps.persistService.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "attendance_events")
public class AttendanceEventEntity {
	@Id
	private String id;
	private String employeeId;
	private Date timestamp;
	private String eventType;
}
