package com.example.reactivepostgresdemo.repository;

import com.example.reactivepostgresdemo.model.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
