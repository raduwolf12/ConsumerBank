package com.example.consumerBank.java.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountRequestDTO.
 */
public class AccountRequestDTO {

/** The account number. */
//	@NotEmpty(message = "account number cannot be empty")
	@NotNull
	private Long accountNumber;

/** The balance. */
//	@NotEmpty(message = "balance number cannot be empty")
	@NotNull
	@Min(value = 1000, message = "balance cannot be less than 1000")
	private double balance;
	
	/** The customer id. */
	private Integer customerId;
	
	/** The account type. */
	@NotNull
	private String accountType;

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
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
}
