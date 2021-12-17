package com.example.consumerBank.java.dto;

public class AccountResponseDTO {

	private Integer accountId;

	private Long accountNumber;

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

}
