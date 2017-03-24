package com.fedich.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private double price;

//    @OneToMany(mappedBy = "product")
//    @ManyToMany(mappedBy = "order")
//    private Set<Order> orders;

    @JsonIgnore
    public Set<Customer> getCustomers() {
        return customers;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private Set<Customer> customers;
}
