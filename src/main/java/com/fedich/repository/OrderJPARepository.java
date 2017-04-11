package com.fedich.repository;

import com.fedich.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderJPARepository extends JpaRepository<Order, Long> {

    Collection<Order> findByCustomerId (Long id);
}
