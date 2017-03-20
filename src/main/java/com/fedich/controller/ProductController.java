package com.fedich.controller;

import com.fedich.model.Product;
import com.fedich.repository.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductJPA productDAO;

    @Autowired
    public ProductController(ProductJPA productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping
    @ResponseBody
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Product getById(@PathVariable long id) {
        return productDAO.findOne(id);
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long id) {
        productDAO.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@RequestBody Product product) {
        productDAO.saveAndFlush(product);
    }

    @PostMapping(value = "/update")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Product product) {
        productDAO.saveAndFlush(product);
    }
}
