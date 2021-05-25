package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceivingDetailTest {

    @Test
    void getAndSetQuantity() {
        int quantity = 0;
        ReceivingDetail receivingDetail = new ReceivingDetail();
        receivingDetail.setQuantity(quantity);
        assertEquals(quantity, receivingDetail.getQuantity());

    }


    @Test
    void getAndSetProduct() {
        Product product = new Product();
        ReceivingDetail receivingDetail = new ReceivingDetail();
        receivingDetail.setProduct(product);
        assertEquals(product, receivingDetail.getProduct());
    }


    @Test
    void getAndSetId() {
        Long receivingDetailId = 0L;
        ReceivingDetail receivingDetail  = new ReceivingDetail();
        receivingDetail.setId(receivingDetailId);
        assertEquals(receivingDetailId, receivingDetail.getId());

    }


    @Test
    void getAndSetReceivingNote() {
        ReceivingNote receivingNote = new ReceivingNote();
        ReceivingDetail receivingDetail  = new ReceivingDetail();
        receivingDetail.setReceivingNote(receivingNote);
        assertEquals(receivingNote, receivingDetail.getReceivingNote());
    }

}