package com.ps.common.models;

import java.util.Date;

import lombok.Data;

@Data
public class AttendanceEvent {
	private String id;
	private String employeeId;
	private Date timestamp;
	private String eventType;
}
