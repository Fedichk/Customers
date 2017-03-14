package com.fedich.controller;

import com.fedich.model.Product;
import com.fedich.repository.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductJPA productDAO;

    @RequestMapping(value="/create/{name}/{price}")
    public String create(Product product){
        productDAO.saveAndFlush(product);
        return "redirect:/products";
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("products", productDAO.findAll());
        return "products";
    }
}
