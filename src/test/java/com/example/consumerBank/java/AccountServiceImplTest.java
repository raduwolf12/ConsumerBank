package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.exceptions.AccountNotExistException;
import com.example.consumerBank.java.repository.AccountRepository;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.TransactionService;
import com.example.consumerBank.java.service.impl.AccountServiceImpl;
import com.example.consumerBank.java.service.impl.CustomerServiceImpl;
import com.example.consumerBank.java.service.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	AccountRequestDTO accountRequestDTO;

	Account account;

	Account savedAccount;

	@BeforeEach
	public void setUp() {

		account = new Account();
		account.setAccountId(1);
		account.setAccountNumber(1234L);
		account.setAccountType("DEBIT");
		account.setBalance(1200);
		account.setCustomer(null);

		savedAccount = new Account();
		savedAccount.setAccountId(2);
		savedAccount.setAccountNumber(1234L);
		savedAccount.setAccountType("CREDIT");
		savedAccount.setBalance(2000);
		savedAccount.setCustomer(null);

		accountRequestDTO = new AccountRequestDTO();
		accountRequestDTO.setAccountNumber(1234L);
		accountRequestDTO.setAccountType("CREDIT");
		accountRequestDTO.setBalance(2000);
		accountRequestDTO.setCustomerId(1);
	}

	@Test
	public void saveAccountData() throws CustomerNotFoundException {

		Customer customer = new Customer();
		customer.setCustomerName("Ana");
		customer.setAddress("Brasov");
		customer.setPhoneNo("12345678");
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer));

		AccountResponseDTO result = accountServiceImpl.saveAccountData(accountRequestDTO);

		assertEquals(1234L, result.getAccountNumber());

	}

	@Test
	public void getAccounts() {
		List<AccountResponseDTO> lists = new ArrayList<AccountResponseDTO>();
		lists.add(new AccountResponseDTO(1, 123L));

		when(accountRepository.findAccounts()).thenReturn(lists);

		List<AccountResponseDTO> result = accountServiceImpl.getAccounts();

		assertEquals(123L, result.get(0).getAccountNumber());

	}

	@Test
	public void getAccountsException() {
		List<AccountResponseDTO> lists = new ArrayList<AccountResponseDTO>();

		when(accountRepository.findAccounts()).thenReturn(lists);
		try {
			List<AccountResponseDTO> result = accountServiceImpl.getAccounts();

		} catch (Exception e) {
			assertEquals("No accounts found",e.getMessage());
		}

	}

	@Test
	public void findAccountByAccountNumber() {
		AccountResponse accountResponse = new AccountResponse(123L, 1000, "DEBIT");

		when(accountRepository.findByAccountNumber(anyLong())).thenReturn(accountResponse);

		AccountResponse result = accountServiceImpl.findAccountByAccountNumber(1L);

		assertEquals(accountResponse.getAccountNumber(), result.getAccountNumber());
		assertEquals(accountResponse.getAccountType(), result.getAccountType());
		assertEquals(accountResponse.getBalance(), result.getBalance());
		assertEquals(accountResponse.getCustomerNumber(), result.getCustomerNumber());

	}

	@Test
	public void delete() {

		String result = accountServiceImpl.delete(1);

		assertEquals("Account was deleted!", result);

	}

}
