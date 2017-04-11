package com.fedich.controller;

import com.fedich.repository.OrderJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderJPARepository orderRepository;

    @Autowired
    public OrderController(OrderJPARepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
