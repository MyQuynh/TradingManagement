package com.example.demo.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="receiving_detail")
public class ReceivingDetail {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="quantity")
    private int quantity;


    @OneToMany(mappedBy = "receiving_detail")
    private Collection<Product> products;


    // Getter setter, constructure
    public ReceivingDetail(int quantity, Collection<Product> products) {
        this.quantity = quantity;
        this.products = products;
    }

    public ReceivingDetail() {

    }
}
