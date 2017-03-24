package com.fedich.controller;

import com.fedich.model.Product;
import com.fedich.repository.ProductJPARepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductJPARepository productDAO;

    public ProductController(ProductJPARepository productDAO) {
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

    @GetMapping(value = "/find/{name}")
    @ResponseBody
    public Collection<Product> getByName(@PathVariable String name) {
        return productDAO.findByName(name);
    }

    @GetMapping(value = "/find/price/{price}")
    @ResponseBody
    public Collection<Product> getByPrice(@PathVariable Double price)
    {
        return productDAO.findByPrice(price);
    }
}
