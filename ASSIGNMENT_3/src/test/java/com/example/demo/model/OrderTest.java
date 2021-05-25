package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getAndSetId() {
        Long orderId = 0L;
        Order order = new Order();
        order.setId(orderId);
        assertEquals(orderId, order.getId());
    }


    @Test
    void getAndSetDate() {
        String date = "2020-02-02";
        Order order = new Order();
        order.setDate(date);
        assertEquals(order.getDate(), date);
    }


    @Test
    void getAndSetStaff() {
        Staff staff = new Staff();
        Order order = new Order();
        order.setStaff(staff);
        assertEquals(order.getStaff(), staff);
    }


    @Test
    void getAndSetProvider() {
        Provider provider = new Provider();
        Order order = new Order();
        order.setProvider(provider);
        assertEquals(order.getProvider(), provider);
    }


    @Test
    void getAndSetOrderDetails() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Order order = new Order();
        order.setOrderDetails(orderDetailList);
        assertEquals(order.getOrderDetails().size(), orderDetailList.size());
    }

}