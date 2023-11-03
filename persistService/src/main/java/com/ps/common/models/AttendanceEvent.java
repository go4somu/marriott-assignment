package com.ps.common.models;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceEvent {
	private String id;
	private String employeeId;
	private Date timestamp;
	private String eventType;
}
