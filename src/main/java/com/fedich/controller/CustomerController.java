package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.repository.CustomerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerJPA customerDAO;

    @Autowired
    public CustomerController(CustomerJPA customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping
    @ResponseBody
    public List<Customer> getAll() {
        return customerDAO.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Customer getById(@PathVariable long id) {
        return customerDAO.findOne(id);
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long id) {
        customerDAO.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@RequestBody Customer customer) {
        customerDAO.saveAndFlush(customer);
    }

    @RequestMapping(value = "/create/{firstName}")
    public String create(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return "redirect:/customers";
    }
}
