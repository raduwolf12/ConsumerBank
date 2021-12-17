package com.example.consumerBank.java.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	
	private Long accountNumber;
	private double balance;

	private String accountType;
	
	@OneToMany(mappedBy="account")
	private Set<Transaction> transaction;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	@JsonIgnore
	private Customer customer;

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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Set<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}
}
