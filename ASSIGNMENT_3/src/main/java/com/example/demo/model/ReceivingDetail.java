package com.example.demo.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="receiving_detail")
public class ReceivingDetail implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivingDetail that = (ReceivingDetail) o;
        return id == that.id &&
                quantity == that.quantity &&
                product.equals(that.product) &&
                receivingNote.equals(that.receivingNote);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, product, receivingNote);
    }
}
