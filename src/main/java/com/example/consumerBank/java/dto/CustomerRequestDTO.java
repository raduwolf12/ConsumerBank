package com.example.consumerBank.java.dto;

import java.util.List;

public class CustomerRequestDTO {

	private String customerName;

	private String phoneNo;

	private String address;
	
	private List<AccountRequestDTO> accountRequestDTOs;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<AccountRequestDTO> getAccountRequestDTOs() {
		return accountRequestDTOs;
	}

	public void setAccountRequestDTOs(List<AccountRequestDTO> accountRequestDTOs) {
		this.accountRequestDTOs = accountRequestDTOs;
	}

}
