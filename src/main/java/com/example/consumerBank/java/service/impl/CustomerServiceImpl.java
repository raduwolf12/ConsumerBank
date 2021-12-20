package com.example.consumerBank.java.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.example.consumerBank.java.dto.AccountRequestDTO;
import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransactionRequestDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionServiceImpl transactionService;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	protected Account toEntity(AccountRequestDTO dto) {
		return modelMapper().map(dto, Account.class);
	}

	protected List<Account> toEntities(List<AccountRequestDTO> dtos) {
		return dtos.stream().map(this::toEntity).collect(Collectors.toList());
	}

	@Override
	public CustomerResponseDTO saveCustomerData(CustomerRequestDTO customerRequestDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerRequestDTO, customer); // to copy all info to one another

		if (!customerRequestDTO.getAccountRequestDTOs().isEmpty()) {
			List<AccountRequestDTO> accountRequestDTOs = customerRequestDTO.getAccountRequestDTOs();
//			for(AccountRequestDTO dto : accountRequestDTOs) {
//				customer.getAccounts().add(null)
//			}
			List<Account> accounts = toEntities(accountRequestDTOs);
			customer.setAccounts(accounts);

		}

		customerRepository.save(customer);

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		BeanUtils.copyProperties(customer, customerResponseDTO);

		return customerResponseDTO;

	}

	@Override
	public List<CustomerResponseDTO> getCustomerDetails() {
		List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();
		List<?> list = customerRepository.findAll();
		Iterator<?> it = list.iterator();

		while (it.hasNext()) {
			CustomerResponseDTO responseDTO = new CustomerResponseDTO();
			BeanUtils.copyProperties(it.next(), responseDTO);
			customerResponseDTOList.add(responseDTO);
		}

		return customerResponseDTOList;
	}

	@Override
	public CustomerResponseDTO getCustomerDetails(Integer customerId) throws CustomerNotFoundException {
		Customer customer = new Customer();
		CustomerResponseDTO responseDTO = new CustomerResponseDTO();

		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isEmpty()) {
			throw new CustomerNotFoundException("Customer doesn't exist for the Id: " + customerId);
		}
		customer = optional.get();
		BeanUtils.copyProperties(customer, responseDTO);
		return responseDTO;
	}

	@Override
	public List<CustomerResponse> getCustomerDetails(String name) {

		return customerRepository.findByCustomerNameContaining(name);
	}

	@Override
	public String delete(Integer customerId) {
		customerRepository.deleteById(customerId);
		return "Customer was succesfully deleted!";
	}

	@Override
	public CustomerResponse getCustomerData(String phoneNo, String Adress) {
		return customerRepository.findCustomerData(phoneNo, Adress);
	}

	@Override
	public String transferFunds(Integer customerId, TransferDTO transferDTO) throws CustomerNotFoundException {
		Customer customer = new Customer();

		Optional<Customer> optional = customerRepository.findById(customerId);
		if (!optional.isPresent()) {
			throw new CustomerNotFoundException("Customer with id: " + customerId + " is not present");
		}
		customer = optional.get();

		customer.getAccounts().stream()
				.filter(account -> transferDTO.getAccountIdSource().equals(account.getAccountId())).findAny()
				.ifPresent(sourceAccount -> {
					sourceAccount.substractFromBalance(transferDTO.getAmount());

					transactionService.saveTransactionData(createTransaction(sourceAccount, transferDTO.getAmount()));
				});

		customer.getAccounts().stream()
				.filter(account -> transferDTO.getAccountIdTarget().equals(account.getAccountId())).findAny()
				.ifPresent(targetAccount -> {
					targetAccount.addToBalance(transferDTO.getAmount());

					transactionService.saveTransactionData(createTransaction(targetAccount, transferDTO.getAmount()));
				});
		customerRepository.save(customer);
		
		return "Transfer was succesfull!";
	}

	private String setTransactionType(String cardType) {
		if (cardType.equals("DEBIT"))
			return "DEBIT";
		return "CREDIT";
	}

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
