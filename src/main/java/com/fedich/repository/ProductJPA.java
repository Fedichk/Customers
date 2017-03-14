package com.fedich.repository;

import com.fedich.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPA extends JpaRepository<Product, Long> {
}
