package com.fedich.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    //    @Id
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;

//    @Column(name = "date")
//    private LocalDate orderedDate;

//    @Column(name = "count")
//    private int count;
}
