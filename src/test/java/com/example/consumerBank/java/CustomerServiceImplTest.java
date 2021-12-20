package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.TransactionService;
import com.example.consumerBank.java.service.impl.CustomerServiceImpl;
import com.example.consumerBank.java.service.impl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;

	@Mock
	TransactionServiceImpl transactionService;

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	CustomerRequestDTO customerRequestDTO;

	Customer customer;

	Customer savedCustomer;

	Customer completedCustomer;

	@BeforeEach
	public void setUp() {
		customerRequestDTO = new CustomerRequestDTO();
		customerRequestDTO.setCustomerName("Ana");
		customerRequestDTO.setAddress("Brasov");
		customerRequestDTO.setPhoneNo("12345678");
		customerRequestDTO
				.setAccountRequestDTOs(new ArrayList<AccountRequestDTO>(Arrays.asList(new AccountRequestDTO())));

		customer = new Customer();
		customer.setCustomerName("Ana");
		customer.setAddress("Brasov");
		customer.setPhoneNo("12345678");

		savedCustomer = new Customer();
		savedCustomer.setCustomerName("Ana");
		savedCustomer.setAddress("Brasov");
		savedCustomer.setPhoneNo("12345678");
		savedCustomer.setCustomerId(1);

		completedCustomer = new Customer();
		completedCustomer.setCustomerName("Ana");
		completedCustomer.setAddress("Brasov");
		completedCustomer.setPhoneNo("12345678");
		completedCustomer.setCustomerId(2);

		Account accountSource = new Account();
		accountSource.setAccountId(1);
		accountSource.setAccountNumber(1234L);
		accountSource.setAccountType("DEBIT");
		accountSource.setBalance(1200);
		accountSource.setCustomer(completedCustomer);

		Account accountTarget = new Account();
		accountTarget.setAccountId(2);
		accountTarget.setAccountNumber(1234L);
		accountTarget.setAccountType("CREDIT");
		accountTarget.setBalance(2000);
		accountTarget.setCustomer(completedCustomer);

		completedCustomer.setAccounts(new ArrayList<Account>(Arrays.asList(accountSource, accountTarget)));
	}

	@Test
	public void saveCustomerDataTest() {
//		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		when(customerRepository.save(any(Customer.class))).thenAnswer(i -> {
			Customer customer = i.getArgument(0);
			customer.setCustomerId(1);
			return customer;
		});

		CustomerResponseDTO result = customerServiceImpl.saveCustomerData(customerRequestDTO);

		assertEquals("Ana", result.getCustomerName());
		verify(customerRepository).save(any(Customer.class));

	}

	@Test
	public void getCustomerDetails() {
		List<Customer> list = new ArrayList<Customer>(Arrays.asList(customer));
		when(customerRepository.findAll()).thenReturn(list);

		List<CustomerResponseDTO> result = customerServiceImpl.getCustomerDetails();
		assertEquals("Ana", result.get(0).getCustomerName());

	}

	@Test
	public void getCustomerDetailsByIdAccepted() throws CustomerNotFoundException {

		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(savedCustomer));

		CustomerResponseDTO result = customerServiceImpl.getCustomerDetails(1);

		assertEquals("Ana", result.getCustomerName());

	}

	@Test
	public void getCustomerDetailsByName() {
		when(customerRepository.findByCustomerNameContaining(anyString()))
				.thenReturn(new ArrayList<CustomerResponse>(Arrays.asList(new CustomerResponse() {

					@Override
					public String getPhoneNo() {
						return "12345678";
					}

					@Override
					public String getCustomerName() {
						return "Ana";
					}

					@Override
					public String getAddress() {
						return "Brasov";
					}

					@Override
					public String getAccounts() {
						return null;
					}
				})));

		List<CustomerResponse> result = customerServiceImpl.getCustomerDetails("Ana");

		assertEquals("Ana", result.get(0).getCustomerName());
	}

	@Test
	public void getCustomerData() {
		when(customerRepository.findCustomerData(anyString(), anyString())).thenReturn(new CustomerResponse() {

			@Override
			public String getPhoneNo() {
				return "12345678";
			}

			@Override
			public String getCustomerName() {
				return "Ana";
			}

			@Override
			public String getAddress() {
				return "Brasov";
			}

			@Override
			public String getAccounts() {
				return null;
			}
		});

		CustomerResponse result = customerServiceImpl.getCustomerData("12345678", "Brasov");

		assertEquals("Ana", result.getCustomerName());
	}

	@Test
	public void delete() {
		String result = customerServiceImpl.delete(1);

		assertEquals("Customer was succesfully deleted!", result);
	}

	@Test
	public void transferFunds() throws CustomerNotFoundException {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(completedCustomer));

		when(transactionService.saveTransactionData(any(TransactionRequestDTO.class)))
				.thenReturn(new TransactionResponseDTO());

		TransferDTO dto = new TransferDTO(1,1,2);
//		dto.setAmount(100);
//		dto.setAccountIdSource(1);
//		dto.setAccountIdTarget(2);

		String result = customerServiceImpl.transferFunds(1, dto);

		assertEquals("Transfer was succesfull!", result);
	}

}
