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

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="receivingNote_id", referencedColumnName = "id")
    private ReceivingNote receivingNote;

    // Getter setter, constructure
    public ReceivingDetail() {
        super();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReceivingNote getReceivingNote() {
        return receivingNote;
    }

    public void setReceivingNote(ReceivingNote receivingNote) {
        this.receivingNote = receivingNote;
    }
}
