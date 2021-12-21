package com.example.consumerBank.java.service;

import java.text.ParseException;
import java.util.List;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface TransactionService.
 */
public interface TransactionService {

	/**
	 * Save transaction data.
	 *
	 * @param transactionRequestDTO the transaction request DTO
	 * @return the transaction response DTO
	 */
	TransactionResponseDTO saveTransactionData(TransactionRequestDTO transactionRequestDTO);

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	List<TransactionResponseDTO> getTransactions();

	/**
	 * Delete.
	 *
	 * @param transactionId the transaction id
	 * @return the string
	 */
	String delete(Integer transactionId);

	/**
	 * Gets the transaction.
	 *
	 * @param transactionId the transaction id
	 * @return the transaction
	 */
	TransactionResponseDTO getTransaction(Integer transactionId);

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
	List<TransactionResponseDTO> getTransactionInInterval(Integer customerId, String startDate, String endDate)
			throws CustomerNotFoundException, ParseException;

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
	long getTransactionInIntervalTotal(Integer customerId, String startDate, String endDate)
			throws CustomerNotFoundException, ParseException;

	/**
	 * Gets the transaction by month.
	 *
	 * @param customerId the customer id
	 * @param month the month
	 * @return the transaction by month
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	List<TransactionResponseDTO> getTransactionByMonth(Integer customerId, Integer month)
			throws CustomerNotFoundException;

}
