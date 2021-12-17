package com.example.consumerBank.java.service;

import java.util.List;

import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

public interface AccountService {
	AccountResponseDTO saveAccountData(AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException;

	List<AccountResponseDTO> getAccounts();

	AccountResponse findAccountByAccountNumber(long accountNumber);

	void delete(Integer accountId);
}
