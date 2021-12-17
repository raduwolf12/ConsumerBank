package com.example.consumerBank.java.exceptions;

public class AccountNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountNotExistException(String s) {
		super(s);
	}
}