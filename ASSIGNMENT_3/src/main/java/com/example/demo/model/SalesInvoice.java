package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="sales_invoice")
public class SalesInvoice {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="date")
    private String date;

    @Column(name="staff_name")
    private String staff_name;

    @OneToOne
    private Customer customer;

    public SalesInvoice(String date, String staff_name, Customer customer) {
        this.date = date;
        this.staff_name = staff_name;
        this.customer = customer;
    }

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

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
