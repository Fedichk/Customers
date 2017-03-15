package com.fedich.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String firstName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(long id) {
        this.id = id;
    }

    public Customer(String firstName) {
        this.firstName = firstName;
    }
}
