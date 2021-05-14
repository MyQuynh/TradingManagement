package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private float price;

    @Column(name="total_value")
    private float total_value;

    // Not yet implementation
    @OneToOne
    private Product product;

}
