package com.fedich.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private long id;

    @NotNull
    @Getter @Setter
    private String firstName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    @Getter @Setter
    private Set<Product> products;

    public Customer() {
    }

    public Customer(long id) {
        this.id = id;
    }

    public Customer(String firstName, Set<Product> products) {
        this.firstName = firstName;
        this.products = products;
    }

    public Customer(String firstName) {
        this.firstName = firstName;
    }
}
