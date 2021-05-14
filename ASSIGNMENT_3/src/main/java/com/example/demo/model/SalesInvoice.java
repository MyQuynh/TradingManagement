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


}
