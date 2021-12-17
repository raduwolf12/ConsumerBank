package com.example.consumerBank.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	@Query(value = "select new com.example.consumerBank.java.dto.AccountResponseDTO(a.accountId, a.accountNumber) from Account a")
	List<AccountResponseDTO> findAccounts();
	
	AccountResponse findByAccountNumber(Long accountNumber);
}
