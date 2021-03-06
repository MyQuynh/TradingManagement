package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="fax")
    private String fax;

    @Column(name="email")
    private String email;

    @Column(name="contactPerson")
    private String contactPerson;

    @OneToMany(mappedBy = "customer")
    private List<SalesInvoice> salesInvoiceList;

    public Customer(long id, String firstName, String lastName, String address, String phone, String fax, String email, String contactPerson) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.contactPerson = contactPerson;
    }

    public Customer() {
        super();
    }

//    public Customer(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
//    }

    public Long getId() {
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SalesInvoice> getSalesInvoiceList() {
        return salesInvoiceList;
    }

    public void setSalesInvoiceList(List<SalesInvoice> salesInvoiceList) {
        this.salesInvoiceList = salesInvoiceList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", salesInvoiceList=" + salesInvoiceList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return id == that.id &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                phone.equals(that.phone) &&
                fax.equals(that.fax) &&
                email.equals(that.email) &&
                contactPerson.equals(that.contactPerson) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, phone, fax, email, contactPerson);
    }
}