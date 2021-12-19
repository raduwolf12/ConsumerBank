package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.entity.Transaction;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountEntityTest {

	@Autowired
	private TestEntityManager entityManager;

	private Account account;

	private Transaction transaction;

	private Customer customer;

	@BeforeEach
	public void setUp() {

		customer = new Customer();
		customer.setAddress(null);
		customer.setCustomerId(1);
		customer.setCustomerName("Test");
		customer.setPhoneNo("0123456");

		transaction = new Transaction();
		transaction.setAmount(123);
		transaction.setTransactionId(12);
		transaction.setTransactionNumber("123");
		transaction.setTransactionType("DEFAULT");

		account = new Account();
		account.setAccountId(12);
		account.setAccountNumber(1L);
		account.setAccountType("DEFAULT");
		account.setBalance(10000);

		customer.setAccounts(null);

		account.setTransaction(new HashSet<>(Arrays.asList(transaction)));

	}

	@Test
	public void testAccountEntity() {
//		Customer savedCustomer = this.entityManager.persistAndFlush(customer);

		Account savedAccount = this.entityManager.persistFlushFind(account);
		assertNotNull(savedAccount);
	}
}
