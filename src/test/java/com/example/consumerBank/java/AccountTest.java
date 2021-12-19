package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Transaction;

public class AccountTest {

	Account account;
	
	@BeforeEach
	public void setUp() {
		account = new Account();
	}

	@Test
	public void testSetGetAccountEntity() {
		
		account.setAccountId(12);
		account.setAccountNumber(1L);
		account.setAccountType("DEFAULT");
		account.setBalance(10000);
		account.setTransaction(null);

		assertEquals(12, account.getAccountId());
		assertEquals(1L, account.getAccountNumber());
		assertEquals("DEFAULT", account.getAccountType());
		assertEquals(10000, account.getBalance());
		assertEquals(null, account.getTransaction());
	}
}
