package com.example.consumerBank.java.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountResponse.
 */
public class AccountResponse {

	/** The account number. */
	private Long accountNumber;
	
	/** The balance. */
	private double balance;
	
	/** The account type. */
	private String accountType;

	/**
	 * Instantiates a new account response.
	 *
	 * @param accountNumber the account number
	 * @param balance the balance
	 * @param accountType the account type
	 */
	public AccountResponse(Long accountNumber, double balance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
	}

	/**
	 * Gets the customer number.
	 *
	 * @return the customer number
	 */
	public Long getCustomerNumber() {
		return accountNumber;
	}

	/**
	 * Sets the customer number.
	 *
	 * @param accountNumber the new customer number
	 */
	public void setCustomerNumber(Long accountNumber) {
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
}
