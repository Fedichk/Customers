package com.fedich.controller;

import com.fedich.model.Product;
import com.fedich.repository.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductJPA productDAO;

    @GetMapping
    @ResponseBody
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long id) {
        productDAO.delete(id);
    }

    @RequestMapping(value = "/create/{name}/{price}")
    public String create(Product product) {
        productDAO.saveAndFlush(product);
        return "redirect:/products";
    }
    @RequestMapping(value = "/{id}")
    public String getById(@PathVariable long id, Model model) {
        model.addAttribute("products",productDAO.findOne(id));
        return "products";
    }
}
