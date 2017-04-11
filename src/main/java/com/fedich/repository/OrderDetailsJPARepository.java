package com.fedich.repository;

import com.fedich.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderDetailsJPARepository extends JpaRepository<OrderDetails, Long> {

    Collection<OrderDetails> findAllByOrderId (Long id);
}
