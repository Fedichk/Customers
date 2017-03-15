package com.fedich.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private double price;

    @OneToMany(mappedBy = "product")
    @Getter
    @Setter
    private Set<Order> orders;

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
