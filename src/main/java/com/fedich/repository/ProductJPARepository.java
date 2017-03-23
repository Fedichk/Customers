package com.fedich.repository;

import com.fedich.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJPARepository extends JpaRepository<Product, Long> {

    List<Product> findByName (String name);

    List<Product> findByPrice (Double price);
}
