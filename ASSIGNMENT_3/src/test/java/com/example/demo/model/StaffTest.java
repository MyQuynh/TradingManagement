package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {


    @Test
    void getAndSetId() {
        Long staffId = 0L;
        Staff staff = new Staff();
        staff.setId(staffId);
        assertEquals(staffId, staff.getId());

    }

    @Test
    void getAndSetFirstName() {
        String firstName = "Test";
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        assertEquals(firstName, staff.getFirstName());

    }


    @Test
    void getAndSetLastName() {
        String lastName = "Test";
        Staff staff = new Staff();
        staff.setLastName(lastName);
        assertEquals(lastName, staff.getLastName());
    }


    @Test
    void getAndSetAddress() {
        String address = "Test";
        Staff staff = new Staff();
        staff.setAddress(address);
        assertEquals(address, staff.getAddress());
    }


    @Test
    void getAndSetPhone() {
        String phone = "Test";
        Staff staff = new Staff();
        staff.setPhone(phone);
        assertEquals(phone, staff.getPhone());
    }


    @Test
    void getAndSetEmail() {
        String email = "Test";
        Staff staff = new Staff();
        staff.setEmail(email);
        assertEquals(email, staff.getEmail());
    }


    @Test
    void getAndSetReceivingNotes() {
        List<ReceivingNote> receivingNotes = new ArrayList<>();
        Staff staff = new Staff();
        staff.setReceivingNotes(receivingNotes);
        assertEquals(receivingNotes.size(), staff.getReceivingNotes().size());
    }


    @Test
    void getAndSetDeliveryNotes() {
        List<DeliveryNote> deliveryNotes = new ArrayList<>();
        Staff staff = new Staff();
        staff.setDeliveryNotes(deliveryNotes);
        assertEquals(deliveryNotes.size(), staff.getDeliveryNotes().size());
    }


    @Test
    void getAndSetSalesInvoices() {
        List<SalesInvoice> salesInvoices = new ArrayList<>();
        Staff staff = new Staff();
        staff.setSalesInvoices(salesInvoices);
        assertEquals(salesInvoices.size(), staff.getSalesInvoices().size());
    }


}