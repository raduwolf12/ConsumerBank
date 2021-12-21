package com.example.consumerBank.java.dto;

import org.springframework.beans.factory.annotation.Value;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerResponse.
 */
public interface CustomerResponse {
	
	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	@Value("#{target.customerName}")
	String getCustomerName();

	/**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
	@Value("#{target.phoneNo}")
	String getPhoneNo();

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Value("#{target.address}")
	String getAddress();

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	@Value("#{target.accounts}")
	String getAccounts();

}
