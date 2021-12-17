package com.example.consumerBank.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<CustomerResponseDTO> findByCustomerName(String name);

	List<CustomerResponse> findByCustomerNameContaining(String name);
		
	@Query(value="select c from Customer c where c.phoneNo= :phoneNo and c.address = :address")
	CustomerResponse findCustomerData(String phoneNo, String address);

}
