package com.example.consumerBank.java.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountResponseDTO.
 */
public class AccountResponseDTO {

	/** The account id. */
	private Integer accountId;

	/** The account number. */
	private Long accountNumber;

	/**
	 * Instantiates a new account response DTO.
	 */
	public AccountResponseDTO() {
	}

	/**
	 * Instantiates a new account response DTO.
	 *
	 * @param accountId the account id
	 * @param accountNumber the account number
	 */
	public AccountResponseDTO(Integer accountId, Long accountNumber) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
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
