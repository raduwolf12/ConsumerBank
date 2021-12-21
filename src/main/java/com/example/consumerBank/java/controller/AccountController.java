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

// TODO: Auto-generated Javadoc
/**
 * The Class AccountController.
 */
@RestController
public class AccountController {
	
	/** The account service. */
	@Autowired
	AccountService accountService;

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Save account data.
	 *
	 * @param accountRequestDTO the account request DTO
	 * @return the response entity
	 * @throws CustomerNotFoundException the customer not found exception
	 */
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

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	@GetMapping("/accounts")
	public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
		return new ResponseEntity<List<AccountResponseDTO>>(accountService.getAccounts(), HttpStatus.ACCEPTED);
	}

	/**
	 * Find account by account number.
	 *
	 * @param accountNumber the account number
	 * @return the response entity
	 */
	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<AccountResponse> findAccountByAccountNumber(@PathVariable long accountNumber) {
		return new ResponseEntity<AccountResponse>(accountService.findAccountByAccountNumber(accountNumber),
				HttpStatus.ACCEPTED);
	}

	/**
	 * Update account data.
	 *
	 * @param accountRequestDTO the account request DTO
	 * @return the response entity
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@PutMapping("/accounts")
	public ResponseEntity<AccountResponseDTO> updateAccountData(@Valid @RequestBody AccountRequestDTO accountRequestDTO)
			throws CustomerNotFoundException {
		AccountResponseDTO accountResponseDTO = accountService.saveAccountData(accountRequestDTO);
		return new ResponseEntity<>(accountResponseDTO, HttpStatus.ACCEPTED);
	}

	/**
	 * Delete customer by id.
	 *
	 * @param accountId the account id
	 * @return the response entity
	 */
	@DeleteMapping("/accounts/{accountId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Integer accountId) {

		return new ResponseEntity<>(accountService.delete(accountId), HttpStatus.ACCEPTED);
	}

}
