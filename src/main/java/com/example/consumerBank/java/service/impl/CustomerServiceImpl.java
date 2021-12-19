package com.example.consumerBank.java.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.entity.Account;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.CustomerService;

@Service
public class CustomerServiceImpl  implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

//	@Autowired
//	AccountRepository accountRepository;

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
		
//		if(!customerRequestDTO.getAccountRequestDTOs().isEmpty()) {
//			List<AccountRequestDTO> accountRequestDTOs = customerRequestDTO.getAccountRequestDTOs();
////			for(AccountRequestDTO dto : accountRequestDTOs) {
////				customer.getAccounts().add(null)
////			}
//			List<Account> accounts= toEntities(accountRequestDTOs);
//			customer.setAccounts(accounts);
//			
//		}
		
		customerRepository.save(customer);

		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		BeanUtils.copyProperties(customer, customerResponseDTO);

		return customerResponseDTO;

	}

	@Override
	public List<CustomerResponseDTO> getCustomerDetails() {
		List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();
		Iterator<?> it = customerRepository.findAll().iterator();

		while (it.hasNext()) {
			CustomerResponseDTO responseDTO = new CustomerResponseDTO();
			BeanUtils.copyProperties(it.next(), responseDTO);
			customerResponseDTOList.add(responseDTO);
		}

		return customerResponseDTOList;
	}

	@Override
	public CustomerResponseDTO getCustomerDetails(Integer customerId) {
		Customer customer = new Customer();
		CustomerResponseDTO responseDTO = new CustomerResponseDTO();

		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isPresent()) {
			customer = optional.get();
		}
		BeanUtils.copyProperties(customer, responseDTO);
		return responseDTO;
	}

	@Override
	public List<CustomerResponse> getCustomerDetails(String name) {

		return customerRepository.findByCustomerNameContaining(name);
	}

	@Override
	public void delete(Integer customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public CustomerResponse getCustomerData(String phoneNo, String Adress) {
		return customerRepository.findCustomerData(phoneNo, Adress);
	}

	@Override
	public void transferFunds(Integer customerId, TransferDTO transferDTO) {
		Customer customer = new Customer();

		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isPresent()) {
			customer = optional.get();

			Account sourceAccount = customer.getAccounts().stream()
					.filter(account -> transferDTO.getAccountIdSource().equals(account.getAccountId())).findAny()
					.orElse(null);

			Account targetAccount = customer.getAccounts().stream()
					.filter(account -> transferDTO.getAccountIdTarget().equals(account.getAccountId())).findAny()
					.orElse(null);

//			Account sourceAccount = accountRepository.findById(transferDTO.getAccountIdSource()).get();
//			Account targetAccount = accountRepository.findById(transferDTO.getAccountIdTarget()).get();
			
			sourceAccount.substractFromBalance(transferDTO.getAmount());
			targetAccount.addToBalance(transferDTO.getAmount());

		}

	}
}
