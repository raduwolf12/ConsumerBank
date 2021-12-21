package com.example.consumerBank.java.service;

import java.util.List;

import com.example.consumerBank.java.dto.CustomerRequestDTO;
import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerService.
 */
public interface CustomerService {
	
	/**
	 * Save customer data.
	 *
	 * @param customerRequestDTO the customer request DTO
	 * @return the customer response DTO
	 */
	CustomerResponseDTO saveCustomerData(CustomerRequestDTO customerRequestDTO);

	/**
	 * Gets the customer details.
	 *
	 * @return the customer details
	 */
	List<CustomerResponseDTO> getCustomerDetails();

	/**
	 * Gets the customer details.
	 *
	 * @param customerId the customer id
	 * @return the customer details
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	CustomerResponseDTO getCustomerDetails(Integer customerId) throws CustomerNotFoundException;

	/**
	 * Gets the customer details.
	 *
	 * @param name the name
	 * @return the customer details
	 */
	List<CustomerResponse> getCustomerDetails(String name);

	/**
	 * Gets the customer data.
	 *
	 * @param phoneNo the phone no
	 * @param Adress the adress
	 * @return the customer data
	 */
	CustomerResponse getCustomerData(String phoneNo, String Adress);

	/**
	 * Delete.
	 *
	 * @param customerId the customer id
	 * @return the string
	 */
	String delete(Integer customerId);

	/**
	 * Transfer funds.
	 *
	 * @param customerId the customer id
	 * @param transferDTO the transfer DTO
	 * @return the string
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	String transferFunds(Integer customerId, TransferDTO transferDTO) throws CustomerNotFoundException;

}
