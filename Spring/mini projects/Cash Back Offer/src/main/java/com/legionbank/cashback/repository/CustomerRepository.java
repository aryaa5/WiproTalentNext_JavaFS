package com.legionbank.cashback.repository;

import com.legionbank.cashback.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
