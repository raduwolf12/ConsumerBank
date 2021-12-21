package com.example.consumerBank.java.service;

import java.util.List;

import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface AccountService.
 */
public interface AccountService {

	/**
	 * Save account data.
	 *
	 * @param accountRequestDTO the account request DTO
	 * @return the account response DTO
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	AccountResponseDTO saveAccountData(AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException;

	/**
	 * Gets the accounts.
	 *
	 * @return the accounts
	 */
	List<AccountResponseDTO> getAccounts();

	/**
	 * Find account by account number.
	 *
	 * @param accountNumber the account number
	 * @return the account response
	 */
	AccountResponse findAccountByAccountNumber(long accountNumber);

	/**
	 * Delete.
	 *
	 * @param accountId the account id
	 * @return the string
	 */
	String delete(Integer accountId);
}
