package com.example.consumerBank.java.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	private String phoneNo;
	private String address;
//	@EmbeddedId
//	private CustomerKey customerKey;

//	private Address homeAddress;
//	
//	@AttributeOverrides(value = {
//			@AttributeOverride(column = @Column(name="officeCity"), name = "city"),
//			@AttributeOverride(column = @Column(name="officeState"), name = "state")
//	})
//	private Address officeAddress;

//	@ElementCollection
//	List<Address> addressList = new ArrayList<Address>();
//
//	@OneToMany(mappedBy = "customer")
//	private List<Account> accounts;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}