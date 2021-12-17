package com.example.consumerBank.java.dto;

public class AccountResponseDTO {

	private Integer accountId;

	private Long accountNumber;

	private double balance;

	private Integer customerId;

	private String accountType;

	public AccountResponseDTO() {
	}

	public AccountResponseDTO(Integer accountId, Long accountNumber) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	

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
