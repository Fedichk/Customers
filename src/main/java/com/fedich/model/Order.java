package com.fedich.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Product product;

    @Column(name = "date")
    @Getter
    @Setter
    private LocalDate orderedDate;

    @Column(name = "count")
    @Getter
    @Setter
    private int count;
}
