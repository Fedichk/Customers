package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.repository.CustomerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerJPA customerDAO;

    @RequestMapping(value="/create/{firstName}")
    public String create(Customer customer){
        customerDAO.saveAndFlush(customer);
        return "redirect:/customers";
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("customers", customerDAO.findAll());
        return "customers";
    }


}
