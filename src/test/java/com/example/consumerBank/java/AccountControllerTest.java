package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.consumerBank.java.controller.AccountController;
import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
	@Mock
	AccountService accountService;

	@InjectMocks
	AccountController accountController;

	AccountRequestDTO accountRequestDTO;

	@BeforeEach
	public void setUp() {
		accountRequestDTO = new AccountRequestDTO();
		accountRequestDTO.setAccountNumber(1234L);
		accountRequestDTO.setAccountType("DEBIT");
		accountRequestDTO.setBalance(1233);
		accountRequestDTO.setCustomerId(1);
	}

	@Test
	@DisplayName("Save account")
	public void saveAccountData() throws CustomerNotFoundException {
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		accountResponseDTO.setAccountId(1);
		accountResponseDTO.setAccountNumber(1234L);

		when(accountService.saveAccountData(accountRequestDTO)).thenReturn(accountResponseDTO);

		ResponseEntity<AccountResponseDTO> result = accountController.saveAccountData(accountRequestDTO);

		assertEquals(1234L, result.getBody().getAccountNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	
	@Test
	@DisplayName("Save account")
	public void saveAccountDataException() throws CustomerNotFoundException {
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		accountResponseDTO.setAccountId(1);
		accountResponseDTO.setAccountNumber(1234L);

		when(accountService.saveAccountData(accountRequestDTO)).thenThrow(new CustomerNotFoundException(null));

		try {
			accountController.saveAccountData(accountRequestDTO);			
		}
		catch (Exception e) {
			assertEquals("Customer doesn't exist for the Id: 1", e.getMessage());
		}		
	}
	

	@Test
	@DisplayName("Get account")
	public void getAccounts() {
		List<AccountResponseDTO> accounts = new ArrayList<AccountResponseDTO>();

		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		accountResponseDTO.setAccountId(1);
		accountResponseDTO.setAccountNumber(1234L);
		accounts.add(accountResponseDTO);

		when(accountService.getAccounts()).thenReturn(accounts);

		ResponseEntity<List<AccountResponseDTO>> result = accountController.getAccounts();

		assertEquals(1234L, result.getBody().get(0).getAccountNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Get account by account number")
	public void findAccountByAccountNumber() {

		when(accountService.findAccountByAccountNumber(anyLong()))
				.thenReturn(new AccountResponse(1234L, 1233, "DEBIT"));

		ResponseEntity<AccountResponse> result = accountController.findAccountByAccountNumber(1);

		assertEquals(1234L, result.getBody().getAccountNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Update account")
	public void updateAccountData() throws CustomerNotFoundException {
		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		accountResponseDTO.setAccountId(1);
		accountResponseDTO.setAccountNumber(1255L);

		accountRequestDTO.setAccountNumber(1255L);

		when(accountService.saveAccountData(accountRequestDTO)).thenReturn(accountResponseDTO);

		ResponseEntity<AccountResponseDTO> result = accountController.updateAccountData(accountRequestDTO);

		assertEquals(1255L, result.getBody().getAccountNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Delete account")
	public void deleteAccount() throws CustomerNotFoundException {

		when(accountService.delete(anyInt())).thenReturn("Account was deleted!");

		ResponseEntity<String> result = accountController.deleteCustomerById(1);

		assertEquals("Account was deleted!", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

}
