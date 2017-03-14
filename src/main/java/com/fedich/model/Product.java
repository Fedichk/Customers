package com.fedich.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private long id;

    @NotNull
    @Getter @Setter
    private String name;

    @NotNull
    @Getter @Setter
    private double price;

    @ManyToMany(mappedBy = "products")
    @Getter @Setter
    private Set<Customer> customers;

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, Set<Customer> customers) {
        this.name = name;
        this.price = price;
        this.customers = customers;
    }
}
