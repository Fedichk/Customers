package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.model.Order;
import com.fedich.model.OrderDetails;
import com.fedich.model.Product;
import com.fedich.repository.CustomerJPARepository;
import com.fedich.repository.OrderDetailsJPARepository;
import com.fedich.repository.OrderJPARepository;
import com.fedich.repository.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    private final CustomerJPARepository customerRepository;
    private final ProductJPARepository productRepository;
    private final OrderJPARepository orderRepository;
    private final OrderDetailsJPARepository detailsRepository;

    @Autowired
    public OrderDetailsController(CustomerJPARepository customerRepository, ProductJPARepository productRepository, OrderJPARepository orderRepository, OrderDetailsJPARepository detailsRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.detailsRepository = detailsRepository;
    }

    @PostMapping
    @ResponseBody
    public Long save(@RequestBody OrderDetails orderDetails) {
        System.out.println(orderDetails);
        Customer customer = customerRepository.findOne(orderDetails.getOrder().getCustomer().getId());
        Product product = productRepository.findOne(orderDetails.getProduct().getId());
        Long orderId = orderDetails.getOrder().getId();
        Order order;
        if (orderId == 0) {
            order = new Order();
            order.setCustomer(customer);
            orderId = orderRepository.saveAndFlush(order).getId();
            order = orderRepository.findOne(orderId);
        } else {
            System.out.println(orderId);
            order = orderRepository.findOne(orderId);
        }
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        orderDetails.setOrderedDate(new Date());
        detailsRepository.saveAndFlush(orderDetails);
        return orderId;
    }
}
