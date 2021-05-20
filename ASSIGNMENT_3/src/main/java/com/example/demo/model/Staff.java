package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "staff")
    private List<ReceivingNote> receivingNotes;

    @OneToMany(mappedBy = "staff")
    private List<DeliveryNote> deliveryNotes;

    @OneToMany(mappedBy = "staff")
    private List<SalesInvoice> salesInvoices;

    // Setters, getters and constructors
    public Staff(){
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReceivingNote> getReceivingNotes() {
        return receivingNotes;
    }

    public void setReceivingNotes(List<ReceivingNote> receivingNotes) {
        this.receivingNotes = receivingNotes;
    }

    public List<DeliveryNote> getDeliveryNotes() {
        return deliveryNotes;
    }

    public void setDeliveryNotes(List<DeliveryNote> deliveryNotes) {
        this.deliveryNotes = deliveryNotes;
    }

    public List<SalesInvoice> getSalesInvoices() {
        return salesInvoices;
    }

    public void setSalesInvoices(List<SalesInvoice> salesInvoices) {
        this.salesInvoices = salesInvoices;
    }
}
