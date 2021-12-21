package com.example.consumerBank.java.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.consumerBank.java.entity.Transaction;

/**
 * The Interface TransactionRepository.
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
