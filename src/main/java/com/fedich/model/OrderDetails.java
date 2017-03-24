package com.fedich.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetails implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Customer product;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Customer order;

    @Column(name = "date")
    private LocalDate orderedDate;

    @Column(name = "count")
    private int count;
}
