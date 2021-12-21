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

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
@Entity
public class Account {

	/** The account id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	/** The account number. */
	private Long accountNumber;
	
	/** The balance. */
	private double balance;

/** The account type. */
//	debit or credit
	private String accountType;

	/** The transaction. */
	@OneToMany(mappedBy = "account")
	private Set<Transaction> transaction;

	/** The customer. */
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	@JsonIgnore
	private Customer customer;

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * Sets the account id.
	 *
	 * @param accountId the new account id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public Long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber the new account number
	 */
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the account type.
	 *
	 * @param accountType the new account type
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public Set<Transaction> getTransaction() {
		return transaction;
	}

	/**
	 * Sets the transaction.
	 *
	 * @param transaction the new transaction
	 */
	public void setTransaction(Set<Transaction> transaction) {
		this.transaction = transaction;
	}

	/**
	 * Adds the to balance.
	 *
	 * @param amount the amount
	 */
	public void addToBalance(double amount) {
		this.balance = balance + amount;
	}

	/**
	 * Substract from balance.
	 *
	 * @param amount the amount
	 */
	public void substractFromBalance(double amount) {
		this.balance = balance - amount;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
