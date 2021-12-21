package com.example.consumerBank.java.controller;

import java.util.List;

import javax.validation.Valid;

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

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerController.
 */
@RestController
@Validated
public class CustomerController {
	
	/** The customer service. */
	@Autowired
	CustomerService customerService;

	/**
	 * Save customer data.
	 *
	 * @param customerRequestDTO the customer request DTO
	 * @return the response entity
	 */
	@PostMapping("/customers")
	public ResponseEntity<CustomerResponseDTO> saveCustomerData(
			@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO customerResponseDTO = customerService.saveCustomerData(customerRequestDTO);
		return new ResponseEntity<CustomerResponseDTO>(customerResponseDTO, HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the customer details.
	 *
	 * @param name the name
	 * @return the customer details
	 */
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerResponse>> getCustomerDetails(@RequestParam String name) {
		return new ResponseEntity<List<CustomerResponse>>(customerService.getCustomerDetails(name),
				HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the customer data.
	 *
	 * @param phoneNo the phone no
	 * @param address the address
	 * @return the customer data
	 */
	@GetMapping("/customers/data")
	public ResponseEntity<CustomerResponse> getCustomerData(@RequestParam String phoneNo,
			@RequestParam String address) {
		return new ResponseEntity<CustomerResponse>(customerService.getCustomerData(phoneNo, address),
				HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the customer details.
	 *
	 * @return the customer details
	 */
	@GetMapping("/customers/all")
	public ResponseEntity<List<CustomerResponseDTO>> getCustomerDetails() {
		return new ResponseEntity<List<CustomerResponseDTO>>(customerService.getCustomerDetails(), HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the customer details.
	 *
	 * @param customerId the customer id
	 * @return the customer details
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerResponseDTO> getCustomerDetails(@PathVariable Integer customerId)
			throws CustomerNotFoundException {
		return new ResponseEntity<>(customerService.getCustomerDetails(customerId), HttpStatus.ACCEPTED);
	}

	/**
	 * Update customer data.
	 *
	 * @param customerRequestDTO the customer request DTO
	 * @return the response entity
	 */
	@PutMapping("/customers")
	public ResponseEntity<CustomerResponseDTO> updateCustomerData(
			@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
		CustomerResponseDTO customerResponseDTO = customerService.saveCustomerData(customerRequestDTO);
		return new ResponseEntity<>(customerResponseDTO, HttpStatus.ACCEPTED);
	}

	/**
	 * Delete customer by id.
	 *
	 * @param customerId the customer id
	 * @return the response entity
	 */
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer customerId) {

		return new ResponseEntity<>(customerService.delete(customerId), HttpStatus.ACCEPTED);
	}

	/**
	 * Transfer funds.
	 *
	 * @param customerId the customer id
	 * @param transferDTO the transfer DTO
	 * @return the response entity
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@PostMapping("/customers/{customerId}")
	public ResponseEntity<String> transferFunds(@PathVariable Integer customerId,
			@Valid @RequestBody TransferDTO transferDTO) throws CustomerNotFoundException {

		return new ResponseEntity<>(customerService.transferFunds(customerId, transferDTO), HttpStatus.ACCEPTED);
	}

}
