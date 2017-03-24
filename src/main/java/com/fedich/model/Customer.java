package com.fedich.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

//    @JsonIgnore
//    public Set<Product> getProducts() {
//        return products;
//    }

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "orders",
//            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
//    private Set<Product> products;
}
