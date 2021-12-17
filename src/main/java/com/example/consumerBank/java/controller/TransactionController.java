package com.example.consumerBank.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransactionResponseDTO;
import com.example.consumerBank.java.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping("/transactions ")
	public ResponseEntity<TransactionResponseDTO> saveTransactionData(
			@RequestBody TransactionRequestDTO transactionRequestDTO) {
		TransactionResponseDTO responseDTO = transactionService.saveTransactionData(transactionRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionResponseDTO>> getTransactions() {
		return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/transactions/{transactionId}")
	public ResponseEntity<TransactionResponseDTO> getTransaction(@PathVariable Integer transactionId) {
		
		return new ResponseEntity<>(transactionService.getTransaction(transactionId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/transactions ")
	public ResponseEntity<TransactionResponseDTO> updateTransactionData(
			@RequestBody TransactionRequestDTO transactionRequestDTO) {
		TransactionResponseDTO responseDTO = transactionService.saveTransactionData(transactionRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity<String> deleteTransaction(@PathVariable Integer transactionId) {
		transactionService.delete(transactionId);
		return new ResponseEntity<>("Transaction was deleted", HttpStatus.ACCEPTED);
	}

}
