package com.example.consumerBank.java.dto;

import java.util.Set;

import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.entity.Transaction;

public class AccountResponse {

	private Long accountNumber;
	private double balance;
	private String accountType;

	public AccountResponse(Long accountNumber, double balance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
}
