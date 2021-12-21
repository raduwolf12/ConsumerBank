package com.example.consumerBank.java.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferDTO.
 */
public class TransferDTO {

	/** The account id source. */
	@NotNull
	Integer accountIdSource;
	
	/** The account id target. */
	@NotNull
	Integer accountIdTarget;
	
	/** The amount. */
	@NotNull
	@Min(1)
	double amount;

	/**
	 * Instantiates a new transfer DTO.
	 */
	public TransferDTO() {

	}

	/**
	 * Instantiates a new transfer DTO.
	 *
	 * @param accountIdSource the account id source
	 * @param accountIdTarget the account id target
	 * @param amount the amount
	 */
	public TransferDTO(Integer accountIdSource, Integer accountIdTarget, double amount) {
		this.accountIdSource = accountIdSource;
		this.accountIdTarget = accountIdTarget;
		this.amount = amount;
	}

	/**
	 * Gets the account id source.
	 *
	 * @return the account id source
	 */
	public Integer getAccountIdSource() {
		return accountIdSource;
	}

	/**
	 * Sets the account id source.
	 *
	 * @param accountIdSource the new account id source
	 */
	public void setAccountIdSource(Integer accountIdSource) {
		this.accountIdSource = accountIdSource;
	}

	/**
	 * Gets the account id target.
	 *
	 * @return the account id target
	 */
	public Integer getAccountIdTarget() {
		return accountIdTarget;
	}

	/**
	 * Sets the account id target.
	 *
	 * @param accountIdTarget the new account id target
	 */
	public void setAccountIdTarget(Integer accountIdTarget) {
		this.accountIdTarget = accountIdTarget;
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

}
