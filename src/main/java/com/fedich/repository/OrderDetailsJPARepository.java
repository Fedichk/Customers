package com.fedich.repository;

import com.fedich.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsJPARepository extends JpaRepository<OrderDetails, Long> {
}
