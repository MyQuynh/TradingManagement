package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="sales_invoice")
public class SalesInvoice {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="date")
    private String date;

    @OneToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "saleInvoice")
    @JsonIgnore
    private List<SaleDetail> saleDetailList;

    @Column(name="total_value")
    private float total_value;

    public SalesInvoice(String date, Staff staff, Customer customer, List<SaleDetail> saleDetailList, float total_value) {
        this.date = date;
        this.staff = staff;
        this.customer = customer;
        this.saleDetailList = saleDetailList;
        this.total_value = total_value;
    }

    public SalesInvoice(){
        super();
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SaleDetail> getSaleDetailList() {
        return saleDetailList;
    }

    public void setSaleDetailList(List<SaleDetail> saleDetailList) {
        this.saleDetailList = saleDetailList;
    }

    public float getTotal_value() {
        return total_value;
    }

    public void setTotal_value(float total_value) {
        this.total_value = total_value;
    }
}
