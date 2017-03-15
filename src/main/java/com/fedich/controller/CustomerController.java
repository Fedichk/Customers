package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.repository.CustomerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerJPA customerDAO;

    @RequestMapping(value = "/create/{firstName}")
    public String create(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/{id}")
    public String getById(@PathVariable long id, Model model) {
        model.addAttribute("customers",customerDAO.findOne(id));
        return "customers";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable long id) {
        customerDAO.delete(id);
        return "redirect:/customers";
    }

    @PostMapping
    public String save(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return "redirect:/customers";
    }

//    @GetMapping
//    public String getAll(Model model) {
//        model.addAttribute("customers", customerDAO.findAll());
//        return "customers";
//    }

    @GetMapping
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        list = customerDAO.findAll();
        return list;
    }
}
