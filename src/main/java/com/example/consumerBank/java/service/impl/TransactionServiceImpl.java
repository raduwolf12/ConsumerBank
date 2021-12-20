package com.example.consumerBank.java.service.impl;

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

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

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

	@Override
	public String delete(Integer transactionId) {
		transactionRepository.deleteById(transactionId);
		return "Transaction was deleted!";
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
		responseDTO.setAccountId(transaction.getAccount().getAccountId());

		return responseDTO;
	}

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

	private boolean checkDates(String startD, String endD, Date curentD) throws ParseException {

		Date startDate = new SimpleDateFormat("yyyy-mm-dd").parse(startD);
		Date endDate = new SimpleDateFormat("yyyy-mm-dd").parse(endD);

		Date curentDate = new SimpleDateFormat("yyyy-mm-dd").parse(curentD.toString());

		if (startDate == null) {
			return endDate == null || curentDate.compareTo(endDate) < 0;
		} else if (endDate == null) {
			return startDate.compareTo(curentDate) < 0;
		} else {
			return startDate.compareTo(curentDate) * curentDate.compareTo(endDate) > 0;
		}

	}

	private TransactionResponseDTO convertTransactionToResponseDto(Transaction transaction) {
		TransactionResponseDTO responseDTO = new TransactionResponseDTO();
		BeanUtils.copyProperties(transaction, responseDTO);
		return responseDTO;
	}

}
