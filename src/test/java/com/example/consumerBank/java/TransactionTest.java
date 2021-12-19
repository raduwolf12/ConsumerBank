package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.consumerBank.java.entity.Transaction;

public class TransactionTest {
	Transaction transaction;

	@BeforeEach
	public void setUp() {
		transaction = new Transaction();
	}

	@Test
	public void testSetGetTransaction() {

		transaction = new Transaction();
		transaction.setAmount(123);
		transaction.setTransactionId(12);
		transaction.setTransactionNumber("123");
		transaction.setTransactionType("DEFAULT");

		assertEquals(123, transaction.getAmount());
		assertEquals(12, transaction.getTransactionId());
		assertEquals("123", transaction.getTransactionNumber());
		assertEquals("DEFAULT", transaction.getTransactionType());

	}
}
