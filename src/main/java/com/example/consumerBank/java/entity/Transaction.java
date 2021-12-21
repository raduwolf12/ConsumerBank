package com.example.consumerBank.java.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaction.
 */
@Entity
public class Transaction {

	/** The transaction id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	
	/** The transaction number. */
	private String transactionNumber;
	
	/** The amount. */
	private double amount;
	
	/** The transaction type. */
	private String transactionType;

/** The transaction date. */
//	private Integer accountId;
	private Date transactionDate;

	/** The account. */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "accountId", nullable = false)
	private Account account;

	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public Integer getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets the transaction number.
	 *
	 * @return the transaction number
	 */
	public String getTransactionNumber() {
		return transactionNumber;
	}

	/**
	 * Sets the transaction number.
	 *
	 * @param transactionNumber the new transaction number
	 */
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the transaction type.
	 *
	 * @return the transaction type
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Sets the transaction type.
	 *
	 * @param transactionType the new transaction type
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * Gets the transaction date.
	 *
	 * @return the transaction date
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * Sets the transaction date.
	 *
	 * @param transactionDate the new transaction date
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
}