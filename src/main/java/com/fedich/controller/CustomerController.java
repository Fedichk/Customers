package com.fedich.controller;

import com.fedich.model.Customer;
import com.fedich.repository.CustomerJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerJPA customerDAO;

    @GetMapping
    @ResponseBody
    public List<Customer> getAll() {
        return customerDAO.findAll();
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long id) {
        customerDAO.delete(id);
    }

    @RequestMapping(value = "/create/{firstName}")
    public String create(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/{id}")
    public String getById(@PathVariable long id, Model model) {
        model.addAttribute("customers", customerDAO.findOne(id));
        return "customers";
    }

    @PostMapping
    public String save(Customer customer) {
        customerDAO.saveAndFlush(customer);
        return "redirect:/customers";
    }
}
