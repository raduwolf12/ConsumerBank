package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;
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
import com.example.consumerBank.java.controller.TransactionController;
import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.AccountService;
import com.example.consumerBank.java.service.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
	@Mock
	TransactionService transactionService;

	@InjectMocks
	public TransactionController transactionController;

	TransactionRequestDTO transactionRequestDTO;

	@BeforeEach
	public void setUp() {
		transactionRequestDTO = new TransactionRequestDTO();
		transactionRequestDTO.setAccountId(1);
		transactionRequestDTO.setAmount(120);
		transactionRequestDTO.setTransactionDate(null);
		transactionRequestDTO.setTransactionNumber("12345");
		transactionRequestDTO.setTransactionType("DEBIT");
	}

	@Test
	@DisplayName("Save transaction")
	public void saveTransactionData() throws CustomerNotFoundException {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		transactionResponseDTO.setAccountId(1);
		transactionResponseDTO.setAmount(120);
		transactionResponseDTO.setTransactionDate(null);
		transactionResponseDTO.setTransactionNumber("12345");
		transactionResponseDTO.setTransactionType("DEBIT");

		when(transactionService.saveTransactionData(transactionRequestDTO)).thenReturn(transactionResponseDTO);

		ResponseEntity<TransactionResponseDTO> result = transactionController
				.saveTransactionData(transactionRequestDTO);

		assertEquals("12345", result.getBody().getTransactionNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Get transactions")
	public void getTransactions() {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		transactionResponseDTO.setAccountId(1);
		transactionResponseDTO.setAmount(120);
		transactionResponseDTO.setTransactionDate(null);
		transactionResponseDTO.setTransactionNumber("12345");
		transactionResponseDTO.setTransactionType("DEBIT");

		List<TransactionResponseDTO> responseDTOs = new ArrayList<TransactionResponseDTO>();
		responseDTOs.add(transactionResponseDTO);

		when(transactionService.getTransactions()).thenReturn(responseDTOs);

		ResponseEntity<List<TransactionResponseDTO>> result = transactionController.getTransactions();

		assertEquals("12345", result.getBody().get(0).getTransactionNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Get transaction")
	public void getTransaction() {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		transactionResponseDTO.setAccountId(1);
		transactionResponseDTO.setAmount(120);
		transactionResponseDTO.setTransactionDate(null);
		transactionResponseDTO.setTransactionNumber("12345");
		transactionResponseDTO.setTransactionType("DEBIT");

		when(transactionService.getTransaction(anyInt())).thenReturn(transactionResponseDTO);

		ResponseEntity<TransactionResponseDTO> result = transactionController.getTransaction(1);

		assertEquals("12345", result.getBody().getTransactionNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	
	@Test
	@DisplayName("Delete transaction")
	public void deleteTransaction() {
		when(transactionService.delete(anyInt())).thenReturn("Transaction was deleted!");
		
		ResponseEntity<String> result = transactionController.deleteTransaction(1);

		assertEquals("Transaction was deleted!", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	
	
	@Test
	@DisplayName("Get transaction in interval")
	public void getTransactionsInInterval() throws CustomerNotFoundException, ParseException {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		transactionResponseDTO.setAccountId(1);
		transactionResponseDTO.setAmount(120);
		transactionResponseDTO.setTransactionDate(null);
		transactionResponseDTO.setTransactionNumber("12345");
		transactionResponseDTO.setTransactionType("DEBIT");

		List<TransactionResponseDTO> responseDTOs = new ArrayList<TransactionResponseDTO>();
		responseDTOs.add(transactionResponseDTO);

		when(transactionService.getTransactionInInterval(anyInt(),anyString(),anyString())).thenReturn(responseDTOs);

		ResponseEntity<List<TransactionResponseDTO>> result = transactionController.getTransactionsInInterval(1,"2021-12-13","2021-12-22");

		assertEquals("12345", result.getBody().get(0).getTransactionNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	@Test
	@DisplayName("Get transaction by month")
	public void getTransactionByMonth() throws CustomerNotFoundException, ParseException {
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		transactionResponseDTO.setAccountId(1);
		transactionResponseDTO.setAmount(120);
		transactionResponseDTO.setTransactionDate(null);
		transactionResponseDTO.setTransactionNumber("12345");
		transactionResponseDTO.setTransactionType("DEBIT");

		List<TransactionResponseDTO> responseDTOs = new ArrayList<TransactionResponseDTO>();
		responseDTOs.add(transactionResponseDTO);

		when(transactionService.getTransactionByMonth(anyInt(),anyInt())).thenReturn(responseDTOs);

		ResponseEntity<List<TransactionResponseDTO>> result = transactionController.getTransactionByMonth(1,12);

		assertEquals("12345", result.getBody().get(0).getTransactionNumber());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	@Test
	@DisplayName("Get transaction total in interval")
	public void getTransactionsTotalInInterval() throws CustomerNotFoundException, ParseException {

		when(transactionService.getTransactionInIntervalTotal(anyInt(),anyString(),anyString())).thenReturn(2L);

		ResponseEntity<Long> result = transactionController.getTransactionsTotalInInterval(1,"2021-12-13","2021-12-22");

		assertEquals(2, result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
	
	

}
