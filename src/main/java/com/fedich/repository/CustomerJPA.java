package com.fedich.repository;

import com.fedich.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJPA extends JpaRepository<Customer, Long> {
}
