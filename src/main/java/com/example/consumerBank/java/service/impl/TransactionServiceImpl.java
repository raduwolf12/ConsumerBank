package com.example.consumerBank.java.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.entity.Transaction;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.TransactionRepository;
import com.example.consumerBank.java.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public TransactionResponseDTO saveTransactionData(TransactionRequestDTO transactionRequestDTO) {

		Transaction transaction = new Transaction();
		BeanUtils.copyProperties(transactionRequestDTO, transaction);
		transactionRepository.save(transaction);

		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
		BeanUtils.copyProperties(transaction, transactionResponseDTO);

		return transactionResponseDTO;

	}

	@Override
	public List<TransactionResponseDTO> getTransactions() {

		List<TransactionResponseDTO> transactionResponseDTOs = new ArrayList<>();
		Iterator<?> it = transactionRepository.findAll().iterator();

		while (it.hasNext()) {
			TransactionResponseDTO responseDTO = new TransactionResponseDTO();
			BeanUtils.copyProperties(it.next(), responseDTO);
			transactionResponseDTOs.add(responseDTO);
		}

		return transactionResponseDTOs;
	}

	@Override
	public void delete(Integer transactionId) {
		transactionRepository.deleteById(transactionId);

	}

	@Override
	public TransactionResponseDTO getTransaction(Integer transactionId) {

		Transaction transaction = new Transaction();
		TransactionResponseDTO responseDTO = new TransactionResponseDTO();

		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		if (optional.isPresent()) {
			transaction = optional.get();
		}
		BeanUtils.copyProperties(transaction, responseDTO);

		return responseDTO;
	}

}
