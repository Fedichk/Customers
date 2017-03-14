package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private DAO customerDAO;

    @RequestMapping(value="/create/{firstName}")
    public String create(Customer customer){
        customerDAO.create(customer);
        return "redirect:/customers";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model){
        model.addAttribute("customers", customerDAO.getAll());
        return "customers";
    }
}
