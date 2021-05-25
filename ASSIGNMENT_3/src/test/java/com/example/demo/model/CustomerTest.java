package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void getIdAndSet() {
        int customerId = 3;
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setId(customerId);
        assertEquals(customerId, customer.getId());
    }

    @Test
    void getFirstNameAndSet() {
        String customerFirstName = "Teo";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setFirstName(customerFirstName);
        assertEquals(customerFirstName, customer.getFirstName());
    }

    @Test
    void getLastNameAndSet() {
        String customerLastName = "Le";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setLastName(customerLastName);
        assertEquals(customerLastName, customer.getLastName());
    }

    @Test
    void getAddressAndSet() {

        String customerAddress = "Fake address";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setAddress(customerAddress);
        assertEquals(customerAddress, customer.getAddress());

    }

    @Test
    void getFaxAndSet() {

        String customerFax = "Fake fax";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setFax(customerFax);
        assertEquals(customerFax, customer.getFax());

    }


    @Test
    void getEmailAndSet() {
        String customerEmail = "Fake email";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setEmail(customerEmail);
        assertEquals(customerEmail, customer.getEmail());
    }

    @Test
    void getAndSetContactPerson() {
        String customerContactPerson = "Fake contact person";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setContactPerson(customerContactPerson);
        assertEquals(customerContactPerson, customer.getContactPerson());
    }

    @Test
    void getPhoneAndSet() {

        String customerPhone = "Fake phone";
        Customer customer = new Customer(1,"test", "test","test","test","test", "test","test");
        customer.setPhone(customerPhone);
        assertEquals(customerPhone, customer.getPhone());

    }

    @Test
    void getSalesInvoiceListAndSet() {
        List<SalesInvoice> salesInvoices = new ArrayList<>();
        Customer customer = new Customer();
        customer.setSalesInvoiceList(salesInvoices);
        assertEquals(customer.getSalesInvoiceList().size(), salesInvoices.size());
    }
}