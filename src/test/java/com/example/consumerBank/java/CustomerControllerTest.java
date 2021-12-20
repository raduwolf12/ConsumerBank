package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.consumerBank.java.controller.CustomerController;
import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	CustomerRequestDTO customerRequestDTO;

	@BeforeEach
	public void setUp() {
		customerRequestDTO = new CustomerRequestDTO();
		customerRequestDTO.setCustomerName("Ana");
		customerRequestDTO.setAddress("Brasov");
		customerRequestDTO.setPhoneNo("12345678");

	}

	@Test
	@DisplayName("Save customer")
	public void saveCustomerDataTest() {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerName("Ana");
		customerResponseDTO.setAddress("Brasov");
		customerResponseDTO.setPhoneNo("12345678");

		when(customerService.saveCustomerData(customerRequestDTO)).thenReturn(customerResponseDTO);

		ResponseEntity<CustomerResponseDTO> result = customerController.saveCustomerData(customerRequestDTO);
		assertEquals("Ana", result.getBody().getCustomerName());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Get Customer details")
	public void getCustomerDetails() {

		List<CustomerResponse> customerResponses = new ArrayList<CustomerResponse>();
		customerResponses.add(new CustomerResponse() {

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

		when(customerService.getCustomerDetails(customerRequestDTO.getCustomerName())).thenReturn(customerResponses);

		ResponseEntity<List<CustomerResponse>> result = customerController
				.getCustomerDetails(customerRequestDTO.getCustomerName());
		assertEquals("Ana", result.getBody().get(0).getCustomerName());
		assertEquals("12345678", result.getBody().get(0).getPhoneNo());
		assertEquals("Brasov", result.getBody().get(0).getAddress());

		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Get Customer data")
	public void getCustomerData() {

		CustomerResponse customerResponse = new CustomerResponse() {

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
		};

		when(customerService.getCustomerData(customerRequestDTO.getPhoneNo(), customerRequestDTO.getAddress()))
				.thenReturn(customerResponse);

		ResponseEntity<CustomerResponse> result = customerController.getCustomerData(customerRequestDTO.getPhoneNo(),
				customerRequestDTO.getAddress());
		assertEquals("Ana", result.getBody().getCustomerName());
		assertEquals("12345678", result.getBody().getPhoneNo());
		assertEquals("Brasov", result.getBody().getAddress());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	@DisplayName("Get all customers")
	public void getAllCustomers() {
		List<CustomerResponseDTO> customerResponseDTOs = new ArrayList<CustomerResponseDTO>();

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerName("Ana");
		customerResponseDTO.setAddress("Brasov");
		customerResponseDTO.setPhoneNo("12345678");
		customerResponseDTOs.add(customerResponseDTO);

		when(customerService.getCustomerDetails()).thenReturn(customerResponseDTOs);

		ResponseEntity<List<CustomerResponseDTO>> result = customerController.getCustomerDetails();

		assertEquals("Ana", result.getBody().get(0).getCustomerName());
		assertEquals("12345678", result.getBody().get(0).getPhoneNo());
		assertEquals("Brasov", result.getBody().get(0).getAddress());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	
	@Test
	@DisplayName("Get customer")
	public void getCustomer() throws CustomerNotFoundException {

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerName("Ana");
		customerResponseDTO.setAddress("Brasov");
		customerResponseDTO.setPhoneNo("12345678");
		

		when(customerService.getCustomerDetails(1)).thenReturn(customerResponseDTO);

		ResponseEntity<CustomerResponseDTO> result = customerController.getCustomerDetails(1);

		assertEquals("Ana", result.getBody().getCustomerName());
		assertEquals("12345678", result.getBody().getPhoneNo());
		assertEquals("Brasov", result.getBody().getAddress());
		
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	@Test
	@DisplayName("Update Customer Data")
	public void updateCustomerData()  {

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerName("Maria");
		customerResponseDTO.setAddress("Brasov");
		customerResponseDTO.setPhoneNo("12345678");
		
		customerRequestDTO.setCustomerName("Maria");

		when(customerService.saveCustomerData(customerRequestDTO)).thenReturn(customerResponseDTO);

		ResponseEntity<CustomerResponseDTO> result = customerController.updateCustomerData(customerRequestDTO);

		assertEquals("Maria", result.getBody().getCustomerName());
		assertEquals("12345678", result.getBody().getPhoneNo());
		assertEquals("Brasov", result.getBody().getAddress());
		
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	
	
	@Test
	@DisplayName("Delete Customer")
	public void deleteCustomerById()  {

		when(customerService.delete(1)).thenReturn("Customer was deleted");

		ResponseEntity<String> result = customerController.deleteCustomerById(1);

		assertEquals("Customer was deleted", result.getBody());

		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}
	
	
	@Test
	@DisplayName("Transfer Customer funds")
	public void transferFunds() throws CustomerNotFoundException  {

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		customerResponseDTO.setCustomerName("Maria");
		customerResponseDTO.setAddress("Brasov");
		customerResponseDTO.setPhoneNo("12345678");
		
		customerRequestDTO.setCustomerName("Maria");
		
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setAccountIdSource(1);
		transferDTO.setAccountIdTarget(2);
		transferDTO.setAmount(100);

		when(customerService.transferFunds(1,transferDTO)).thenReturn("Funds were transfered!");

		ResponseEntity<String> result = customerController.transferFunds(1,transferDTO);

		assertEquals("Funds were transfered!", result.getBody());
		
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

}
