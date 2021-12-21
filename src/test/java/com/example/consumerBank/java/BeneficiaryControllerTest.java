package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.consumerBank.java.controller.BeneficiaryController;
import com.example.consumerBank.java.dto.BeneficiaryRequestDTO;
import com.example.consumerBank.java.dto.BeneficiaryResponseDto;
import com.example.consumerBank.java.dto.TransferBeneficiaryDTO;
import com.example.consumerBank.java.dto.TransferDTO;
import com.example.consumerBank.java.exception.CustomerNotFoundException;
import com.example.consumerBank.java.service.BeneficiaryService;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryControllerTest {
	@Mock
	BeneficiaryService beneficiaryService;

	@InjectMocks
	public BeneficiaryController beneficiaryController;

	BeneficiaryRequestDTO beneficiaryRequestDTO;

	@BeforeEach
	public void setUp() {
		beneficiaryRequestDTO = new BeneficiaryRequestDTO();
		beneficiaryRequestDTO.setBenefactorCustomerId(1);
		beneficiaryRequestDTO.setBeneficiryCustomerId(2);
	}

	@Test
	public void saveBeneficiary() throws CustomerNotFoundException {
		BeneficiaryResponseDto beneficiaryResponseDto = new BeneficiaryResponseDto();
		beneficiaryResponseDto.setBenefactorCustomerId(1);
		beneficiaryResponseDto.setBeneficiryCustomerId(2);
		beneficiaryResponseDto.setBeneficiryId(1);

		when(beneficiaryService.saveBeneficiary(beneficiaryRequestDTO)).thenReturn(beneficiaryResponseDto);

		ResponseEntity<BeneficiaryResponseDto> result = beneficiaryController.saveBeneficiary(beneficiaryRequestDTO);

		assertEquals(1, result.getBody().getBenefactorCustomerId());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

	@Test
	public void transferFunds() throws CustomerNotFoundException {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setAccountIdSource(1);
		transferDTO.setAccountIdTarget(2);
		transferDTO.setAmount(100);
		
		TransferBeneficiaryDTO beneficiaryDTO = new TransferBeneficiaryDTO();
		beneficiaryDTO.setBeneficiaryRequestDTO(beneficiaryRequestDTO);
		beneficiaryDTO.setTransferDTO(transferDTO);
			

		when(beneficiaryService.transferFunds(any(TransferDTO.class),any(BeneficiaryRequestDTO.class))).thenReturn("Transfer was succesfull!");

		ResponseEntity<String> result = beneficiaryController.transferFunds(beneficiaryDTO);

		assertEquals("Transfer was succesfull!", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

}
