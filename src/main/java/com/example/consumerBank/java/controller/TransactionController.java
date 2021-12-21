package com.example.consumerBank.java.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.TransactionService;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionController.
 */
@RestController
public class TransactionController {

	/** The transaction service. */
	@Autowired
	TransactionService transactionService;

	/**
	 * Save transaction data.
	 *
	 * @param transactionRequestDTO the transaction request DTO
	 * @return the response entity
	 */
	@PostMapping("/transactions ")
	public ResponseEntity<TransactionResponseDTO> saveTransactionData(
			@RequestBody TransactionRequestDTO transactionRequestDTO) {
		TransactionResponseDTO responseDTO = transactionService.saveTransactionData(transactionRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactions() {
		return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the transaction.
	 *
	 * @param transactionId the transaction id
	 * @return the transaction
	 */
	@GetMapping("/transactions/{transactionId}")
	public ResponseEntity<TransactionResponseDTO> getTransaction(@PathVariable Integer transactionId) {

		return new ResponseEntity<>(transactionService.getTransaction(transactionId), HttpStatus.ACCEPTED);
	}

	/**
	 * Delete transaction.
	 *
	 * @param transactionId the transaction id
	 * @return the response entity
	 */
	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity<String> deleteTransaction(@PathVariable Integer transactionId) {

		return new ResponseEntity<>(transactionService.delete(transactionId), HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the transactions in interval.
	 *
	 * @param customerId the customer id
	 * @param startDate  the start date
	 * @param endDate    the end date
	 * @return the transactions in interval
	 * @throws CustomerNotFoundException the customer not found exception
	 * @throws ParseException            the parse exception
	 */
	@GetMapping("/transactions/{customerId}/{startDate}/{endDate}")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactionsInInterval(@PathVariable Integer customerId,
			@PathVariable String startDate, @PathVariable String endDate)
			throws CustomerNotFoundException, ParseException {

		return new ResponseEntity<List<TransactionResponseDTO>>(
				transactionService.getTransactionInInterval(customerId, startDate, endDate), HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the transaction by month.
	 *
	 * @param customerId the customer id
	 * @param month      the month
	 * @return the transaction by month
	 * @throws CustomerNotFoundException the customer not found exception
	 * @throws ParseException            the parse exception
	 */
	@GetMapping("/transactions/{customerId}/{month}")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactionByMonth(@PathVariable Integer customerId,
			@PathVariable Integer month) throws CustomerNotFoundException, ParseException {

		return new ResponseEntity<List<TransactionResponseDTO>>(
				transactionService.getTransactionByMonth(customerId, month), HttpStatus.ACCEPTED);
	}

	/**
	 * Gets the transactions total in interval.
	 *
	 * @param customerId the customer id
	 * @param startDate  the start date
	 * @param endDate    the end date
	 * @return the transactions total in interval
	 * @throws CustomerNotFoundException the customer not found exception
	 * @throws ParseException            the parse exception
	 */
	@GetMapping("/transactions/total/{customerId}/{startDate}/{endDate}")
	public ResponseEntity<Long> getTransactionsTotalInInterval(@PathVariable Integer customerId,
			@PathVariable String startDate, @PathVariable String endDate)
			throws CustomerNotFoundException, ParseException {

		return new ResponseEntity<>(transactionService.getTransactionInIntervalTotal(customerId, startDate, endDate),
				HttpStatus.ACCEPTED);
	}

}
