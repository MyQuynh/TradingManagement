package com.example.demo.model;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="order_service")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="provider_id", referencedColumnName = "id")
    private Provider provider;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Collection<OrderDetail> orderDetails;

    // Getter and setter constructor
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Collection<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
