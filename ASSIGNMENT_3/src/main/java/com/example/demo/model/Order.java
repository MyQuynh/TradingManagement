package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="orderService")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="date")
    private String date;

    @Column(name="staff")
    private long staff_id;

    @Column(name="provider")
    private long provider_id;

    // Getter and setter constructor
    public Order(String date, long staff_id, long provider_id) {
        this.date = date;
        this.staff_id = staff_id;
        this.provider_id = provider_id;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Collection<OrderDetail> orderDetails;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }

    public long getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(long provider_id) {
        this.provider_id = provider_id;
    }
}
