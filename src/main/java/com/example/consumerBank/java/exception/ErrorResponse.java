package com.example.consumerBank.java.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorResponse {
	private String message;
	private int statuscode;
	private LocalDateTime dateTime;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public LocalDateTime getDateTimel() {
		return dateTime;
	}

	public void setDateTimel(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}