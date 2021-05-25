package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryDetailTest {

    @Test
    void getAndSetId() {
        Long deliveryDetailId = 0L;
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        deliveryDetail.setId(deliveryDetailId);
        assertEquals(deliveryDetail.getId(), deliveryDetailId);
    }


    @Test
    void getAndSetProduct() {
        Product product = new Product();
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        deliveryDetail.setProduct(product);
        assertEquals(deliveryDetail.getProduct(), product);
    }


    @Test
    void getAndSetQuantity() {
        int  deliveryDetailQuantity = 12;
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        deliveryDetail.setQuantity(deliveryDetailQuantity);
        assertEquals(deliveryDetail.getQuantity(), deliveryDetailQuantity);
    }

    @Test
    void getAndSetDeliveryNote() {
        DeliveryNote deliveryNote = new DeliveryNote();
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        deliveryDetail.setDeliveryNote(deliveryNote);
        assertEquals(deliveryDetail.getDeliveryNote(), deliveryNote);
    }


}