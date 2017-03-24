package com.fedich.repository;

import com.fedich.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long> {
}
