package com.example.consumerBank.java.entity.enums;

public enum AccountType {
	DEFAULT(0), CA(1), SA(2);

	private int value;

	private AccountType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
