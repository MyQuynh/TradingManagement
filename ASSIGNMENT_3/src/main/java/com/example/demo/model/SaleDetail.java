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

    @OneToMany(mappedBy = "product")
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "salesInvoice", referencedColumnName = "id")
    private SalesInvoice salesInvoice;

    public SaleDetail(int quantity, float price, List<Product> product, SalesInvoice salesInvoice) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.salesInvoice = salesInvoice;
    }

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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }
}
