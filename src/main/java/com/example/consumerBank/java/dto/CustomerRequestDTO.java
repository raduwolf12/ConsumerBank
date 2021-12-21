package com.example.consumerBank.java.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerRequestDTO.
 */
public class CustomerRequestDTO {
	
	/** The customer name. */
	@NotNull
	private String customerName;
	
	/** The phone no. */
	@NotNull
	private String phoneNo;
	
	/** The address. */
	@NotNull
	private String address;

	/** The account request DT os. */
	private List<AccountRequestDTO> accountRequestDTOs;

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone no.
	 *
	 * @param phoneNo the new phone no
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the account request DT os.
	 *
	 * @return the account request DT os
	 */
	public List<AccountRequestDTO> getAccountRequestDTOs() {
		return accountRequestDTOs;
	}

	/**
	 * Sets the account request DT os.
	 *
	 * @param accountRequestDTOs the new account request DT os
	 */
	public void setAccountRequestDTOs(List<AccountRequestDTO> accountRequestDTOs) {
		this.accountRequestDTOs = accountRequestDTOs;
	}

}
