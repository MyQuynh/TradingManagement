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
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="receivingDetail_id", referencedColumnName = "id")
    private ReceivingDetail receiving_detail;

    public Product(String name, String model, String brand, String company, String description, int quantity, float price, Category category, ReceivingDetail receiving_detail) {
        this.name = name;
        this.model = model;
        this.brand = brand;
        this.company = company;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.receiving_detail = receiving_detail;
    }

    public Product() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ReceivingDetail getReceiving_detail() {
        return receiving_detail;
    }

    public void setReceiving_detail(ReceivingDetail receiving_detail) {
        this.receiving_detail = receiving_detail;
    }
}
