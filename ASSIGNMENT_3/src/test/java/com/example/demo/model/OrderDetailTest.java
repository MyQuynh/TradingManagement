package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailTest {

    @Test
    void getAndSetId() {
        int orderDetailId = 0;
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailId);
        assertEquals(orderDetail.getId(), orderDetailId);
    }

    @Test
    void getAndSetProduct() {
        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product();
        orderDetail.setProduct(product);
        assertEquals(product, orderDetail.getProduct());
    }


    @Test
    void getAndSetQuantity() {
        int orderDetailQuantity = 0;
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(orderDetailQuantity);
        assertEquals(orderDetail.getQuantity(), orderDetailQuantity);
    }


    @Test
    void getAndSetPrice() {
        float orderDetailPrice = 0;
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(orderDetailPrice);
        assertEquals(orderDetail.getPrice(), orderDetailPrice);
    }


    @Test
    void getAndSetOrder() {
        Order order =  new Order();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        assertEquals(orderDetail.getOrder(), order);
    }


}