package com.example.consumerBank.java.service;

import java.text.ParseException;
import java.util.List;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

public interface TransactionService {

	TransactionResponseDTO saveTransactionData(TransactionRequestDTO transactionRequestDTO);

	List<TransactionResponseDTO> getTransactions();

	String delete(Integer transactionId);

	TransactionResponseDTO getTransaction(Integer transactionId);

	List<TransactionResponseDTO> getTransactionInInterval(Integer customerId, String startDate, String endDate)
			throws CustomerNotFoundException, ParseException;

	long getTransactionInIntervalTotal(Integer customerId, String startDate, String endDate)
			throws CustomerNotFoundException, ParseException;

	List<TransactionResponseDTO> getTransactionByMonth(Integer customerId, Integer month)
			throws CustomerNotFoundException;

}
