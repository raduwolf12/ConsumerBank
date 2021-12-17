package com.example.consumerBank.java.dto;

public class AccountResponse {

	private Long accountNumber;
	private double balance;
	private Integer customerId;
	private String accountType;

	public AccountResponse(Long accountNumber, double balance, Integer customerId, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customerId = customerId;
		this.accountType = accountType;
	}

	public Long getCustomerNumber() {
		return accountNumber;
	}

	public void setCustomerNumber(Long accountNumber) {
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
