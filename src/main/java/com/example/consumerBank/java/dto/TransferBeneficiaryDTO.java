package com.example.consumerBank.java.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class TransferBeneficiaryDTO.
 */
public class TransferBeneficiaryDTO {
	
	/** The transfer DTO. */
	private TransferDTO transferDTO;
	
	/** The beneficiary request DTO. */
	private BeneficiaryRequestDTO beneficiaryRequestDTO;

	/**
	 * Gets the transfer DTO.
	 *
	 * @return the transfer DTO
	 */
	public TransferDTO getTransferDTO() {
		return transferDTO;
	}

	/**
	 * Sets the transfer DTO.
	 *
	 * @param transferDTO the new transfer DTO
	 */
	public void setTransferDTO(TransferDTO transferDTO) {
		this.transferDTO = transferDTO;
	}

	/**
	 * Gets the beneficiary request DTO.
	 *
	 * @return the beneficiary request DTO
	 */
	public BeneficiaryRequestDTO getBeneficiaryRequestDTO() {
		return beneficiaryRequestDTO;
	}

	/**
	 * Sets the beneficiary request DTO.
	 *
	 * @param beneficiaryRequestDTO the new beneficiary request DTO
	 */
	public void setBeneficiaryRequestDTO(BeneficiaryRequestDTO beneficiaryRequestDTO) {
		this.beneficiaryRequestDTO = beneficiaryRequestDTO;
	}

}
