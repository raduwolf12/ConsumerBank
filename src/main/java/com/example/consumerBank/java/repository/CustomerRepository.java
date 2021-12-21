package com.example.consumerBank.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.consumerBank.java.dto.CustomerResponse;
import com.example.consumerBank.java.dto.CustomerResponseDTO;
import com.example.consumerBank.java.entity.Customer;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustomerRepository.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	/**
	 * Find by customer name.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<CustomerResponseDTO> findByCustomerName(String name);

	/**
	 * Find by customer name containing.
	 *
	 * @param name the name
	 * @return the list
	 */
	List<CustomerResponse> findByCustomerNameContaining(String name);

	/**
	 * Find customer data.
	 *
	 * @param phoneNo the phone no
	 * @param address the address
	 * @return the customer response
	 */
	@Query(value = "select c from Customer c where c.phoneNo= :phoneNo and c.address = :address")
	CustomerResponse findCustomerData(String phoneNo, String address);

}
