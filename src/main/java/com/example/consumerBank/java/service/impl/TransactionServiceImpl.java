package com.example.consumerBank.java.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.entity.Transaction;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.exceptions.AccountNotExistException;
import com.example.consumerBank.java.repository.AccountRepository;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.repository.TransactionRepository;
import com.example.consumerBank.java.service.TransactionService;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionServiceImpl.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/** The transaction repository. */
	@Autowired
	TransactionRepository transactionRepository;

	/** The account repository. */
	@Autowired
	AccountRepository accountRepository;

	/** The customer repository. */
	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Save transaction data.
	 *
	 * @param transactionRequestDTO the transaction request DTO
	 * @return the transaction response DTO
	 */
	@Override
	public TransactionResponseDTO saveTransactionData(TransactionRequestDTO transactionRequestDTO) {

		Optional<Account> optional = accountRepository.findById(transactionRequestDTO.getAccountId());

		if (optional.isEmpty())
			throw new AccountNotExistException(
					"Customer doesn't exist for the Id: " + transactionRequestDTO.getAccountId());

		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(transactionRequestDTO, transaction);

		transaction.setAccount(optional.get());

		transactionRepository.save(transaction);

		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		BeanUtils.copyProperties(transaction, transactionResponseDTO);

		return transactionResponseDTO;

	}

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	@Override
	public List<TransactionResponseDTO> getTransactions() {

		List<TransactionResponseDTO> transactionResponseDTOs = new ArrayList<>();
//		Iterator<?> it = transactionRepository.findAll().iterator();
		Iterable<Transaction> transactions = transactionRepository.findAll();

		for (Transaction transaction : transactions) {
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
//			Transaction transaction = (Transaction) it.next();
			BeanUtils.copyProperties(transaction, responseDTO);
			responseDTO.setAccountId(transaction.getAccount().getAccountId());
			transactionResponseDTOs.add(responseDTO);
		}

		return transactionResponseDTOs;
	}

	/**
	 * Delete.
	 *
	 * @param transactionId the transaction id
	 * @return the string
	 */
	@Override
	public String delete(Integer transactionId) {
		transactionRepository.deleteById(transactionId);
		return "Transaction was deleted!";
	}

	/**
	 * Gets the transaction.
	 *
	 * @param transactionId the transaction id
	 * @return the transaction
	 */
	@Override
	public TransactionResponseDTO getTransaction(Integer transactionId) {

		Transaction transaction = new Transaction();
		TransactionResponseDTO responseDTO = new TransactionResponseDTO();

		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		if (optional.isPresent()) {
			transaction = optional.get();
		}

		BeanUtils.copyProperties(transaction, responseDTO);
		responseDTO.setAccountId(transaction.getAccount().getAccountId());

		return responseDTO;
	}

	/**
	 * Gets the transaction in interval.
	 *
	 * @param customerId the customer id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the transaction in interval
	 * @throws CustomerNotFoundException the customer not found exception
	 * @throws ParseException the parse exception
	 */
	@Override
	public List<TransactionResponseDTO> getTransactionInInterval(Integer customerId, String startDate, String endDate)
			throws CustomerNotFoundException, ParseException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty()) {
			throw new CustomerNotFoundException("Customer doesn't exist for the Id: " + customerId);
		}

		Customer customer = optional.get();
		List<Transaction> transactions = new ArrayList<Transaction>();

		for (Account account : customer.getAccounts()) {
			for (Transaction transaction : account.getTransaction()) {
				if (checkDates(startDate, endDate, transaction.getTransactionDate())) {
					transactions.add(transaction);
				}

			}
		}

		List<TransactionResponseDTO> dtos = transactions.stream()
				.map(transaction -> convertTransactionToResponseDto(transaction)).collect(Collectors.toList());

		return dtos;
	}

	/**
	 * Gets the transaction by month.
	 *
	 * @param customerId the customer id
	 * @param month the month
	 * @return the transaction by month
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@Override
	public List<TransactionResponseDTO> getTransactionByMonth(Integer customerId, Integer month)
			throws CustomerNotFoundException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty()) {
			throw new CustomerNotFoundException("Customer doesn't exist for the Id: " + customerId);
		}

		Customer customer = optional.get();
		List<Transaction> transactions = new ArrayList<Transaction>();

		for (Account account : customer.getAccounts()) {
			for (Transaction transaction : account.getTransaction()) {

				LocalDate localDate = transaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();
				int transactionMonth = localDate.getMonthValue();

				if (transactionMonth == month) {
					transactions.add(transaction);
				}

			}
		}

		List<TransactionResponseDTO> dtos = transactions.stream()
				.map(transaction -> convertTransactionToResponseDto(transaction)).collect(Collectors.toList());

		return dtos;
	}

	/**
	 * Gets the transaction in interval total.
	 *
	 * @param customerId the customer id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the transaction in interval total
	 * @throws CustomerNotFoundException the customer not found exception
	 * @throws ParseException the parse exception
	 */
	@Override
	public long getTransactionInIntervalTotal(Integer customerId, String startDate, String endDate)
			throws CustomerNotFoundException, ParseException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty()) {
			throw new CustomerNotFoundException("Customer doesn't exist for the Id: " + customerId);
		}

		Customer customer = optional.get();
		List<Transaction> transactions = new ArrayList<Transaction>();

		for (Account account : customer.getAccounts()) {
			for (Transaction transaction : account.getTransaction()) {
				if (checkDates(startDate, endDate, transaction.getTransactionDate())) {
					transactions.add(transaction);
				}

			}
		}

		return transactions.stream().count();
	}

	/**
	 * Check dates.
	 *
	 * @param startD the start D
	 * @param endD the end D
	 * @param curentD the curent D
	 * @return true, if successful
	 * @throws ParseException the parse exception
	 */
	private boolean checkDates(String startD, String endD, Date curentD) throws ParseException {

		Date startDate = new SimpleDateFormat("yyyy-mm-dd").parse(startD);
		Date endDate = new SimpleDateFormat("yyyy-mm-dd").parse(endD);

		String pattern = "yyyy-mm-dd";

		DateFormat df = new SimpleDateFormat(pattern);

		String cDate = df.format(curentD);

		Date curentDate = new SimpleDateFormat("yyyy-mm-dd").parse(cDate);

		if (startDate == null) {
			return endDate == null || curentDate.compareTo(endDate) < 0;
		} else if (endDate == null) {
			return startDate.compareTo(curentDate) < 0;
		} else {
			return startDate.compareTo(curentDate) * curentDate.compareTo(endDate) > 0;
		}

	}

	/**
	 * Convert transaction to response dto.
	 *
	 * @param transaction the transaction
	 * @return the transaction response DTO
	 */
	private TransactionResponseDTO convertTransactionToResponseDto(Transaction transaction) {
		TransactionResponseDTO responseDTO = new TransactionResponseDTO();
		BeanUtils.copyProperties(transaction, responseDTO);
		return responseDTO;
	}

}
