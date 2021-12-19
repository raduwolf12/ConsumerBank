package com.example.consumerBank.java.service;

import java.util.List;

import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

public interface CustomerService {
	CustomerResponseDTO saveCustomerData(CustomerRequestDTO customerRequestDTO);

	List<CustomerResponseDTO> getCustomerDetails();
	
	CustomerResponseDTO getCustomerDetails(Integer customerId) throws CustomerNotFoundException;
	
	List<CustomerResponse> getCustomerDetails(String name);
	
	CustomerResponse getCustomerData(String phoneNo, String Adress);	
	
	void delete(Integer customerId);

	void transferFunds(Integer customerId, TransferDTO transferDTO) throws CustomerNotFoundException;

}
