package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.entity.Transaction;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.AccountRepository;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.repository.TransactionRepository;
import com.example.consumerBank.java.service.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;

	TransactionRequestDTO transactionRequestDTO;

	Transaction transaction;

	Transaction savedTransaction;

	Account account;

	Customer customer;

	@BeforeEach
	public void setUp() throws ParseException {
		transactionRequestDTO = new TransactionRequestDTO();
		transactionRequestDTO.setAccountId(1);
		transactionRequestDTO.setAmount(100);
		transactionRequestDTO.setTransactionDate(null);
		transactionRequestDTO.setTransactionNumber("12345");
		transactionRequestDTO.setTransactionType("DEBIT");

		account = new Account();
		account.setAccountId(1);
		account.setAccountNumber(1234L);
		account.setAccountType("DEBIT");
		account.setBalance(1200);

		String sDate6 = "17-Dec-2021 23:37:50";
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date date1 = formatter1.parse(sDate6);

		transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmount(100);
		transaction.setTransactionDate(date1);
		transaction.setTransactionNumber("12345");
		transaction.setTransactionType("DEBIT");

		savedTransaction = new Transaction();
		savedTransaction.setAccount(account);
		savedTransaction.setAmount(100);
		savedTransaction.setTransactionDate(date1);
		savedTransaction.setTransactionNumber("12345");
		savedTransaction.setTransactionType("DEBIT");

		Set<Transaction> set = new HashSet<Transaction>();
		set.add(transaction);
		account.setTransaction(set);

		customer = new Customer();
		customer.setCustomerName("Ana");
		customer.setAddress("Brasov");
		customer.setPhoneNo("12345678");
		customer.setAccounts(Arrays.asList(account));
	}

	@Test
	public void saveCustomerDataTest() {
		when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));
		when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

		TransactionResponseDTO result = transactionServiceImpl.saveTransactionData(transactionRequestDTO);
		assertEquals(100, result.getAmount());
	}

	@Test
	public void delete() {

		String result = transactionServiceImpl.delete(1);
		assertEquals("Transaction was deleted!", result);
	}

	@Test
	public void getTransactions() {
		when(transactionRepository.findAll()).thenReturn(new ArrayList<Transaction>((Arrays.asList(transaction))));

		List<TransactionResponseDTO> result = transactionServiceImpl.getTransactions();
		assertEquals(100, result.get(0).getAmount());
	}

	@Test
	public void getTransaction() {
		when(transactionRepository.findById(anyInt())).thenReturn(Optional.of(transaction));
		TransactionResponseDTO result = transactionServiceImpl.getTransaction(1);
		assertEquals(100, result.getAmount());
	}

	@Test
	public void getTransactionInInterval() throws CustomerNotFoundException, ParseException {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
		List<TransactionResponseDTO> result = transactionServiceImpl.getTransactionInInterval(1, "2021-12-11",
				"2021-12-20");
		assertEquals(100, result.get(0).getAmount());
	}

	@Test
	public void getTransactionInIntervalTotal() throws CustomerNotFoundException, ParseException {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
		long result = transactionServiceImpl.getTransactionInIntervalTotal(1, "2021-12-11",
				"2021-12-20");
		assertEquals(1, result);

	}

	@Test
	public void getTransactionByMonth() throws CustomerNotFoundException {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));
		List<TransactionResponseDTO> result = transactionServiceImpl.getTransactionByMonth(1, 12);
		assertEquals(100, result.get(0).getAmount());
	}

}
