package com.example.consumerBank.java.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class AccountRequestDTO {
//	@NotEmpty(message = "account number cannot be empty")
	private Long accountNumber;
//	@NotEmpty(message = "balance number cannot be empty")
	@Min(value = 1000, message ="balance cannot be less than 1000" )
	private double balance;
	private Integer customerId;
	private String accountType;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
