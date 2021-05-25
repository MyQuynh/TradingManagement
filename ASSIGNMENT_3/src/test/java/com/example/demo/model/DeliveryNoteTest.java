package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryNoteTest {

    @Test
    void getAndSetId() {
        Long deliveryNoteId = 0L;
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setId(deliveryNoteId);
        assertEquals(deliveryNoteId, deliveryNote.getId());
    }

    @Test
    void getAndSetDate() {
        Date deliveryNoteDate = new Date();
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setDate(deliveryNoteDate);
        assertEquals(deliveryNoteDate, deliveryNote.getDate());
    }


    @Test
    void getAndSetStaff() {
        Staff staff = new Staff();
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setStaff(staff);
        assertEquals(staff, deliveryNote.getStaff());
    }

    @Test
    void getAndSetDeliveryDetails() {
        List<DeliveryDetail> deliveryDetailList = new ArrayList<>();
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setDeliveryDetails(deliveryDetailList);
        assertEquals(deliveryDetailList, deliveryNote.getDeliveryDetails());
    }

}