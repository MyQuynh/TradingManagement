package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="delivery_detail")
public class DeliveryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="product")
    private long product_id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="date")
    private String dare;

    @Column(name="staff")
    private long staff_id;

    // Getter, setter and constructor
    public DeliveryDetail(){

    }

    public DeliveryDetail(long product_id, int quantity, String dare, long staff_id) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.dare = dare;
        this.staff_id = staff_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDare() {
        return dare;
    }

    public void setDare(String dare) {
        this.dare = dare;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
}
