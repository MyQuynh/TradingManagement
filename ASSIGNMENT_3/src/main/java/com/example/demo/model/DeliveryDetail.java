package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="delivery_detail")
public class DeliveryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name="quantity")
    private int quantity;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "deliveryNote_id", referencedColumnName = "id")
    private DeliveryNote deliveryNote;

    // Getter, setter and constructor
    public DeliveryDetail(){
        super();
    }

    public DeliveryDetail(Product product, int quantity, Date date, Staff staff, DeliveryNote deliveryNote) {
        this.product = product;
        this.quantity = quantity;
        this.date = date;
        this.staff = staff;
        this.deliveryNote = deliveryNote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

}
