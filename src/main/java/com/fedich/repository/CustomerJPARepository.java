package com.fedich.repository;

import com.fedich.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJPARepository extends JpaRepository<Customer, Long> {
}
