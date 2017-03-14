package com.fedich.controller;

import com.fedich.model.Product;
import com.fedich.repository.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private DAO productDAO;

    @RequestMapping(value="/create/{name}/{price}")
    public String create(Product product){
        productDAO.create(product);
        return "redirect:/products";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model){
        model.addAttribute("products", productDAO.getAll());
        return "products";
    }
}
