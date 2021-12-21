package com.example.consumerBank.java.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionRequestDTO.
 */
public class TransactionRequestDTO {
	
	/** The transaction number. */
	@NotNull
	private String transactionNumber;
	
	/** The amount. */
	@NotNull
	private double amount;
	
	/** The transaction type. */
	@NotNull
	private String transactionType;
	
	/** The account id. */
	@NotNull
	private Integer accountId;
	
	/** The transaction date. */
	@NotNull
	private Date transactionDate;

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

}
