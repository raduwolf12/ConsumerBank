package com.example.consumerBank.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.CustomerService;

@RestController
@Validated
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<CustomerResponseDTO> saveCustomerData(@RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO customerResponseDTO = customerService.saveCustomerData(customerRequestDTO);
		return new ResponseEntity<>(customerResponseDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerResponse>> getCustomerDetails(@RequestParam String name) {
		return new ResponseEntity<List<CustomerResponse>>(customerService.getCustomerDetails(name),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers/data")
	public ResponseEntity<CustomerResponse> getCustomerData(@RequestParam String phoneNo,
			@RequestParam String address) {
		return new ResponseEntity<CustomerResponse>(customerService.getCustomerData(phoneNo, address),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers/all")
	public ResponseEntity<List<CustomerResponseDTO>> getCustomerDetails() {
		return new ResponseEntity<List<CustomerResponseDTO>>(customerService.getCustomerDetails(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerResponseDTO> getCustomerDetails(@PathVariable Integer customerId) {
		return new ResponseEntity<>(customerService.getCustomerDetails(customerId), HttpStatus.ACCEPTED);
	}

	@PutMapping("/customers")
	public ResponseEntity<CustomerResponseDTO> updateCustomerData(@RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO customerResponseDTO = customerService.saveCustomerData(customerRequestDTO);
		return new ResponseEntity<>(customerResponseDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId) {
		customerService.delete(customerId);
		return new ResponseEntity<>("Customer was deleted", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/customers/{customerId}")
	public ResponseEntity<String> transferFunds(@PathVariable Integer customerId,@RequestBody TransferDTO transferDTO) throws CustomerNotFoundException {
		
		customerService.transferFunds(customerId, transferDTO);
		
		return new ResponseEntity<>("Funds were transfered!", HttpStatus.ACCEPTED);
	}
	
}
