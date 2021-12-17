package com.example.consumerBank.java.dto;

public class TransferDTO {

	Integer accountIdSource;
	Integer accountIdTarget;
	double amount;

	public TransferDTO() {

	}

	public TransferDTO(Integer accountIdSource, Integer accountIdTarget, double amount) {
		this.accountIdSource = accountIdSource;
		this.accountIdTarget = accountIdTarget;
		this.amount = amount;
	}

	public Integer getAccountIdSource() {
		return accountIdSource;
	}

	public void setAccountIdSource(Integer accountIdSource) {
		this.accountIdSource = accountIdSource;
	}

	public Integer getAccountIdTarget() {
		return accountIdTarget;
	}

	public void setAccountIdTarget(Integer accountIdTarget) {
		this.accountIdTarget = accountIdTarget;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
