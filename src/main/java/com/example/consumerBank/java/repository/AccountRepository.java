package com.example.consumerBank.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.consumerBank.java.dto.AccountResponse;
import com.example.consumerBank.java.dto.AccountResponseDTO;
import com.example.consumerBank.java.entity.Account;

// TODO: Auto-generated Javadoc
/**
 * The Interface AccountRepository.
 */
public interface AccountRepository extends CrudRepository<Account, Integer> {

	/**
	 * Find accounts.
	 *
	 * @return the list
	 */
	@Query(value = "select new com.example.consumerBank.java.dto.AccountResponseDTO(a.accountId, a.accountNumber) from Account a")
	List<AccountResponseDTO> findAccounts();

	/**
	 * Find by account number.
	 *
	 * @param accountNumber the account number
	 * @return the account response
	 */
	AccountResponse findByAccountNumber(Long accountNumber);
}
