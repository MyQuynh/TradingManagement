package com.example.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @Column(name="price")
    private float price;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDetail that = (SaleDetail) o;
        return id == that.id &&
                quantity == that.quantity &&
                price == that.price &&
                salesInvoice.equals(that.salesInvoice) &&
                product.equals(that.product);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price, salesInvoice, product);
    }
}
