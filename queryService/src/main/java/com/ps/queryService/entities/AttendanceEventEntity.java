package com.ps.queryService.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "attendance_events")
public class AttendanceEventEntity {
	@Id
	private String id;
	private String employeeId;
	private Date timestamp;
	private String eventType;
}
