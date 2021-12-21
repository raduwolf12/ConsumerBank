package com.example.consumerBank.java.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 */
@Entity
public class Customer {

	/** The customer id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	/** The customer name. */
	private String customerName;
	
	/** The phone no. */
	private String phoneNo;
	
	/** The address. */
	private String address;

	/** The accounts. */
	@OneToMany(mappedBy = "customer")
	private List<Account> accounts;

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
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * Sets the accounts.
	 *
	 * @param accounts the new accounts
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}