package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name="model")
    private String model;

    @Column(name="brand")
    private String brand;

    @Column(name="company")
    private String company;

    @Column(name="description")
    private String description;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private float price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="receiving_detail")
    private ReceivingDetail receiving_detail;


}
