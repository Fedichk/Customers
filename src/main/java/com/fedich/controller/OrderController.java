package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.model.Product;
import com.fedich.repository.CustomerJPARepository;
import com.fedich.repository.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final CustomerJPARepository customerRepository;
    private final ProductJPARepository productRepository;

    @Autowired
    public OrderController(CustomerJPARepository customerRepository, ProductJPARepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@RequestBody Customer customer, Product product) {
        customer.getProducts().add(product);
        customerRepository.saveAndFlush(customer);
        System.out.println(customer);
        System.out.println(product);
    }
}
