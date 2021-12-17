package com.example.consumerBank.java.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phoneNo;
	private String emailId;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
