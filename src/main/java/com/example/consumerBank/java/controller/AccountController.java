package com.example.consumerBank.java.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/accounts")
	public ResponseEntity<AccountResponseDTO> saveAccountData(@Valid @RequestBody AccountRequestDTO accountRequestDTO)
			throws CustomerNotFoundException {

		AccountResponseDTO accountResponseD;
		try {
			accountResponseD = accountService.saveAccountData(accountRequestDTO);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Customer doesn't exist for the Id: " + accountRequestDTO.getCustomerId());

			throw new CustomerNotFoundException(
					"Customer doesn't exist for the Id: " + accountRequestDTO.getCustomerId());
		}

		return new ResponseEntity<AccountResponseDTO>(accountResponseD, HttpStatus.ACCEPTED);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
		return new ResponseEntity<List<AccountResponseDTO>>(accountService.getAccounts(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<AccountResponse> findAccountByAccountNumber(@PathVariable long accountNumber) {
		return new ResponseEntity<AccountResponse>(accountService.findAccountByAccountNumber(accountNumber),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("/accounts")
	public ResponseEntity<AccountResponseDTO> updateCustomerData(@RequestBody AccountRequestDTO accountRequestDTO)
			throws CustomerNotFoundException {
		AccountResponseDTO accountResponseDTO = accountService.saveAccountData(accountRequestDTO);
		return new ResponseEntity<>(accountResponseDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/accounts/{accountId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer accountId) {
		accountService.delete(accountId);
		return new ResponseEntity<>("Customer was deleted", HttpStatus.ACCEPTED);
	}
	


}
