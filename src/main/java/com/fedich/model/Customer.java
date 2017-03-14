package com.fedich.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String firstName;

//    @OneToMany(mappedBy = "customers")
//    @JoinTable(
//            name = "orders",
//            joinColumns = @JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_name")
//    )
//    private Collection<Product> products;

    public Customer () {}

    public Customer (long id) {
        this.id = id;
    }

    public Customer(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
