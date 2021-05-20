package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "salesInvoice", referencedColumnName = "id")
    private SalesInvoice salesInvoice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
