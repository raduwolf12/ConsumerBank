package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.consumerBank.java.dto.BeneficiaryRequestDTO;
import com.example.consumerBank.java.dto.BeneficiaryResponseDto;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.dto.TransferBeneficiaryDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Beneficiry;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.entity.Transaction;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.BeneficiaryRepository;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.TransactionService;
import com.example.consumerBank.java.service.impl.BeneficiaryServiceImpl;
import com.example.consumerBank.java.service.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceImplTest {

	@Mock
	BeneficiaryRepository beneficiaryRepository;

	@Mock
	TransactionService transactionService;

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	BeneficiaryServiceImpl beneficiaryServiceImpl;

	Transaction transaction;

	Account account;

	Customer beneficiryCustomer;

	Customer benefactorCustomer;

	Beneficiry beneficiry;

	Beneficiry savedBeneficiry;

	BeneficiaryRequestDTO beneficiaryRequestDTO;

	@BeforeEach
	public void setUp() throws ParseException {

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

		Set<Transaction> set = new HashSet<Transaction>();
		set.add(transaction);
		account.setTransaction(set);

		beneficiryCustomer = new Customer();
		beneficiryCustomer.setCustomerName("Ana");
		beneficiryCustomer.setAddress("Brasov");
		beneficiryCustomer.setPhoneNo("12345678");
		beneficiryCustomer.setAccounts(Arrays.asList(account));

		benefactorCustomer = new Customer();
		benefactorCustomer.setCustomerName("Maria");
		benefactorCustomer.setAddress("Brasov");
		benefactorCustomer.setPhoneNo("12345678");
		benefactorCustomer.setAccounts(Arrays.asList(account));

		savedBeneficiry = new Beneficiry();
		savedBeneficiry.setBenefactorCustomerId(1);
		savedBeneficiry.setBeneficiryCustomerId(2);
		savedBeneficiry.setBeneficiryId(1);

		beneficiry = new Beneficiry();
		beneficiry.setBenefactorCustomerId(1);
		beneficiry.setBeneficiryCustomerId(2);
		beneficiry.setBeneficiryId(1);

		beneficiaryRequestDTO = new BeneficiaryRequestDTO();
		beneficiaryRequestDTO.setBenefactorCustomerId(1);
		beneficiaryRequestDTO.setBeneficiryCustomerId(2);

	}

	@Test
	public void saveBeneficiary() throws CustomerNotFoundException {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(beneficiryCustomer));
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(benefactorCustomer));

		when(beneficiaryRepository.save(any(Beneficiry.class))).thenReturn(savedBeneficiry);

		BeneficiaryResponseDto result = beneficiaryServiceImpl.saveBeneficiary(beneficiaryRequestDTO);
		assertEquals(1, result.getBenefactorCustomerId());
	}

	@Test
	public void transferFunds() throws CustomerNotFoundException {

		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setAccountIdSource(1);
		transferDTO.setAccountIdTarget(2);
		transferDTO.setAmount(100);

		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(beneficiryCustomer));
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(benefactorCustomer));

		String result = beneficiaryServiceImpl.transferFunds(transferDTO, beneficiaryRequestDTO);

		assertEquals("Transfer was succesfull!", result);
	}

}
