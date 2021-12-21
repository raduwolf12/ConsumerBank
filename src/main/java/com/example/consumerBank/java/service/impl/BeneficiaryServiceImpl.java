package com.example.consumerBank.java.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumerBank.java.dto.BeneficiaryRequestDTO;
import com.example.consumerBank.java.dto.BeneficiaryResponseDto;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Beneficiry;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.BeneficiaryRepository;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.BeneficiaryService;
import com.example.consumerBank.java.service.TransactionService;

// TODO: Auto-generated Javadoc
/**
 * The Class BeneficiaryServiceImpl.
 */
@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	/** The beneficiary repository. */
	@Autowired
	BeneficiaryRepository beneficiaryRepository;

	/** The customer repository. */
	@Autowired
	CustomerRepository customerRepository;

	/** The transaction service. */
	@Autowired
	TransactionService transactionService;

	/**
	 * Save beneficiary.
	 *
	 * @param beneficiaryRequestDTO the beneficiary request DTO
	 * @return the beneficiary response dto
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@Override
	public BeneficiaryResponseDto saveBeneficiary(BeneficiaryRequestDTO beneficiaryRequestDTO)
			throws CustomerNotFoundException {
		Optional<Customer> beneficiryCustomer = customerRepository
				.findById(beneficiaryRequestDTO.getBeneficiryCustomerId());

		if (beneficiryCustomer.isEmpty())
			throw new CustomerNotFoundException(
					"Customer doesn't exist for the Id: " + beneficiaryRequestDTO.getBeneficiryCustomerId());

		Optional<Customer> benefactorCustomer = customerRepository
				.findById(beneficiaryRequestDTO.getBenefactorCustomerId());

		if (benefactorCustomer.isEmpty())
			throw new CustomerNotFoundException(
					"Customer doesn't exist for the Id: " + beneficiaryRequestDTO.getBenefactorCustomerId());

		Beneficiry beneficiry = new Beneficiry();
		BeanUtils.copyProperties(beneficiaryRequestDTO, beneficiry);

		beneficiaryRepository.save(beneficiry);

		BeneficiaryResponseDto beneficiaryResponseDto = new BeneficiaryResponseDto();
		BeanUtils.copyProperties(beneficiry, beneficiaryResponseDto);

		return beneficiaryResponseDto;
	}

	/**
	 * Transfer funds.
	 *
	 * @param transferDTO the transfer DTO
	 * @param beneficiaryRequestDTO the beneficiary request DTO
	 * @return the string
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@Override
	public String transferFunds(TransferDTO transferDTO, BeneficiaryRequestDTO beneficiaryRequestDTO)
			throws CustomerNotFoundException {
		Customer beneficiryCustomer = new Customer();
		Customer benefactorCustomer = new Customer();

		Optional<Customer> optionalBenefactor = customerRepository
				.findById(beneficiaryRequestDTO.getBenefactorCustomerId());
		if (!optionalBenefactor.isPresent()) {
			throw new CustomerNotFoundException(
					"Customer with id: " + beneficiaryRequestDTO.getBenefactorCustomerId() + " is not present");
		}
		benefactorCustomer = optionalBenefactor.get();

		Optional<Customer> optionalBeneficiry = customerRepository
				.findById(beneficiaryRequestDTO.getBeneficiryCustomerId());
		if (!optionalBenefactor.isPresent()) {
			throw new CustomerNotFoundException(
					"Customer with id: " + beneficiaryRequestDTO.getBeneficiryCustomerId() + " is not present");
		}
		beneficiryCustomer = optionalBeneficiry.get();

//		benefactorCustomer.getAccounts().stream()
//				.filter(account -> transferDTO.getAccountIdSource().equals(account.getAccountId())).findAny()
//				.ifPresent(sourceAccount -> {
//					sourceAccount.substractFromBalance(transferDTO.getAmount());
//
//					transactionService.saveTransactionData(createTransaction(sourceAccount, transferDTO.getAmount()));
//				});

//		beneficiryCustomer.getAccounts().stream()
//		.filter(account -> transferDTO.getAccountIdTarget().equals(account.getAccountId())).findAny()
//		.ifPresent(targetAccount -> {
//			targetAccount.addToBalance(transferDTO.getAmount());
//
//			transactionService.saveTransactionData(createTransaction(targetAccount, transferDTO.getAmount()));
//		});

		for (Account account : benefactorCustomer.getAccounts()) {
			if (transferDTO.getAccountIdSource().equals(account.getAccountId())) {
				account.substractFromBalance(transferDTO.getAmount());

				transactionService.saveTransactionData(createTransaction(account, transferDTO.getAmount()));
			}
		}

		for (Account account : beneficiryCustomer.getAccounts()) {
			if (transferDTO.getAccountIdTarget().equals(account.getAccountId())) {
				account.addToBalance(transferDTO.getAmount());

				transactionService.saveTransactionData(createTransaction(account, transferDTO.getAmount()));
			}
		}

		customerRepository.save(beneficiryCustomer);
		customerRepository.save(benefactorCustomer);

		return "Transfer was succesfull!";
	}

	/**
	 * Sets the transaction type.
	 *
	 * @param cardType the card type
	 * @return the string
	 */
	private String setTransactionType(String cardType) {
		if (cardType.equals("DEBIT"))
			return "DEBIT";
		return "CREDIT";
	}

	/**
	 * Creates the transaction.
	 *
	 * @param account the account
	 * @param amount the amount
	 * @return the transaction request DTO
	 */
	private TransactionRequestDTO createTransaction(Account account, double amount) {
		TransactionRequestDTO transactionDto = new TransactionRequestDTO();
		transactionDto.setAmount(amount);
		transactionDto.setTransactionDate(new Date(System.currentTimeMillis()));
		transactionDto.setTransactionNumber(Integer.toString(ThreadLocalRandom.current().nextInt()));

		transactionDto.setAccountId(account.getAccountId());
		transactionDto.setTransactionType(setTransactionType(account.getAccountType()));

		return transactionDto;
	}

}
