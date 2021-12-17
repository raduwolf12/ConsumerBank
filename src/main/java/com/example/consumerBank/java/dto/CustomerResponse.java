package com.example.consumerBank.java.dto;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerResponse {
	@Value("#{target.customerName}")
	String getCustomerName();

	@Value("#{target.phoneNo}")
	String getPhoneNo();

	@Value("#{target.address}")
	String getAddress();
}
