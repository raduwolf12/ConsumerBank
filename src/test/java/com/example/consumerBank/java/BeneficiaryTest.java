package com.example.consumerBank.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.consumerBank.java.entity.Beneficiry;

public class BeneficiaryTest {
	Beneficiry beneficiry;

	@BeforeEach
	public void setUp() {
		beneficiry = new Beneficiry();
	}

	@Test
	public void testSetGetBeneficiry() {
		beneficiry.setBenefactorCustomerId(1);
		beneficiry.setBeneficiryCustomerId(2);
		beneficiry.setBeneficiryId(1);

		assertEquals(1, beneficiry.getBenefactorCustomerId());
		assertEquals(2, beneficiry.getBeneficiryCustomerId());
		assertEquals(1, beneficiry.getBeneficiryId());
	}
}
