package com.example.consumerBank.java.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.AccountRepository;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public AccountResponseDTO saveAccountData(AccountRequestDTO accountRequestDTO) throws CustomerNotFoundException {
		Optional<Customer> optional = customerRepository.findById(accountRequestDTO.getCustomerId());

		if (optional.isEmpty())
			throw new CustomerNotFoundException(
					"Customer doesn't exist for the Id: " + accountRequestDTO.getCustomerId());

		Account account = new Account();
		BeanUtils.copyProperties(accountRequestDTO, account);

		accountRepository.save(account);

		AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
		BeanUtils.copyProperties(account, accountResponseDTO);

		return accountResponseDTO;
	}

	@Override
	public List<AccountResponseDTO> getAccounts() {
		List<AccountResponseDTO> accountResponseDTOs = new ArrayList<>();
		Iterator<?> it = customerRepository.findAll().iterator();

		while (it.hasNext()) {
			AccountResponseDTO responseDTO = new AccountResponseDTO();
			BeanUtils.copyProperties(it.next(), responseDTO);
			accountResponseDTOs.add(responseDTO);
		}

		return accountResponseDTOs;
	}

	@Override
	public AccountResponse findAccountByAccountNumber(long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public void delete(Integer accountId) {
		accountRepository.deleteById(accountId);		
	}

}
