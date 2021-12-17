package com.example.consumerBank.java.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.entity.Customer;
import com.example.consumerBank.java.repository.CustomerRepository;
import com.example.consumerBank.java.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public CustomerResponseDTO saveCustomerData(CustomerRequestDTO customerRequestDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerRequestDTO, customer); // to copy all info to one another
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
}
