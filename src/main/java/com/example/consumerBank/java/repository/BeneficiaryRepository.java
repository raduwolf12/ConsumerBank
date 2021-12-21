package com.example.consumerBank.java.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.consumerBank.java.entity.Beneficiry;

/**
 * The Interface BeneficiaryRepository.
 */
@Repository
public interface BeneficiaryRepository extends CrudRepository<Beneficiry, Integer> {

}
