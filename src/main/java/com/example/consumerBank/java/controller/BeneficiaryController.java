package com.example.consumerBank.java.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumerBank.java.dto.BeneficiaryRequestDTO;
import com.example.consumerBank.java.dto.BeneficiaryResponseDto;
import com.example.consumerBank.java.dto.TransferBeneficiaryDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.BeneficiaryService;

// TODO: Auto-generated Javadoc
/**
 * The Class BeneficiaryController.
 */
@RestController
@Validated
public class BeneficiaryController {
	
	/** The beneficiary service. */
	@Autowired
	BeneficiaryService beneficiaryService;

	/**
	 * Save beneficiary.
	 *
	 * @param beneficiaryRequestDTO the beneficiary request DTO
	 * @return the response entity
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@PostMapping("/beneficiarys")
	public ResponseEntity<BeneficiaryResponseDto> saveBeneficiary(
			@RequestBody BeneficiaryRequestDTO beneficiaryRequestDTO) throws CustomerNotFoundException {
		BeneficiaryResponseDto responseDTO = beneficiaryService.saveBeneficiary(beneficiaryRequestDTO);
		return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
	}

	/**
	 * Transfer funds.
	 *
	 * @param beneficiaryRequestDTO the beneficiary request DTO
	 * @return the response entity
	 * @throws CustomerNotFoundException the customer not found exception
	 */
	@PostMapping("/beneficiarys/transfer")
	public ResponseEntity<String> transferFunds(@Valid @RequestBody TransferBeneficiaryDTO beneficiaryRequestDTO)
			throws CustomerNotFoundException {
		String responseDTO = beneficiaryService.transferFunds(beneficiaryRequestDTO.getTransferDTO(),
				beneficiaryRequestDTO.getBeneficiaryRequestDTO());
		return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
	}

}
