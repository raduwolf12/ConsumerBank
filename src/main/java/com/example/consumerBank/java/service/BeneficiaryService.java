package com.example.consumerBank.java.service;

import com.example.consumerBank.java.dto.BeneficiaryRequestDTO;
import com.example.consumerBank.java.dto.BeneficiaryResponseDto;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Interface BeneficiaryService.
 */
public interface BeneficiaryService {

	/**
	 * Save beneficiary.
	 *
	 * @param beneficiaryRequestDTO the beneficiary request DTO
	 * @return the beneficiary response dto
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	public BeneficiaryResponseDto saveBeneficiary(BeneficiaryRequestDTO beneficiaryRequestDTO)
			throws CustomerNotFoundException;

	/**
	 * Transfer funds.
	 *
	 * @param transferDTO the transfer DTO
	 * @param beneficiaryRequestDTO the beneficiary request DTO
	 * @return the string
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	public String transferFunds(TransferDTO transferDTO, BeneficiaryRequestDTO beneficiaryRequestDTO)
			throws CustomerNotFoundException;

}
