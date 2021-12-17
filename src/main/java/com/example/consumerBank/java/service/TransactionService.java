package com.example.consumerBank.java.service;

import java.util.List;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;

public interface TransactionService {

	TransactionResponseDTO saveTransactionData(TransactionRequestDTO transactionRequestDTO);
	
	List<TransactionResponseDTO> getTransactions();

	void delete(Integer transactionId);

	TransactionResponseDTO getTransaction(Integer transactionId);

}
