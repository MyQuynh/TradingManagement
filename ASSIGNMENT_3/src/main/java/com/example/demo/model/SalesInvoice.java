package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="sales_invoice")
public class SalesInvoice implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public SalesInvoice(long id, String date, Staff staff, Customer customer, float total_value) {
        this.id = id;
        this.date = date;
        this.staff = staff;
        this.customer = customer;
        this.total_value = total_value;
    }

    @OneToMany(mappedBy = "salesInvoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SaleDetail> saleDetails;

    @Column(name = "total_value")
    private float total_value;

    public SalesInvoice() {
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

    public List<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }

    public float getTotal_value() {
        return total_value;
    }

    public void setTotal_value(float total_value) {
        this.total_value = total_value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesInvoice that = (SalesInvoice) o;
        return id == that.id &&
                date.equals(that.date) &&
                staff.equals(that.staff) &&
                customer.equals(that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, staff, customer);

    }

}
