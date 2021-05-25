package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {

    @Test
    void getAndSetId() {
        Long providerId = 0L;
        Provider provider = new Provider();
        provider.setId(providerId);
        assertEquals(providerId, provider.getId());

    }

    @Test
    void getAndSetName() {
        String providerName = "Test";
        Provider provider = new Provider();
        provider.setName(providerName);
        assertEquals(providerName, provider.getName());
    }


    @Test
    void getAndSetAddress() {
        String providerAddress = "Test";
        Provider provider = new Provider();
        provider.setAddress(providerAddress);
        assertEquals(providerAddress, provider.getAddress());
    }


    @Test
    void getAndSetPhone() {
        String providerPhone = "Test";
        Provider provider = new Provider();
        provider.setPhone(providerPhone);
        assertEquals(providerPhone, provider.getPhone());
    }


    @Test
    void getAndSetFax() {
        String providerFax = "Test";
        Provider provider = new Provider();
        provider.setFax(providerFax);
        assertEquals(providerFax, provider.getFax());
    }


    @Test
    void getAndSetEmail() {
        String providerEmail = "Test";
        Provider provider = new Provider();
        provider.setEmail(providerEmail);
        assertEquals(providerEmail, provider.getEmail());
    }


    @Test
    void getAndSetContact_person() {
        String providerContactPerson = "Test";
        Provider provider = new Provider();
        provider.setContact_person(providerContactPerson);
        assertEquals(providerContactPerson, provider.getContact_person());
    }

    @Test
    void getAndSetOrderList() {
        Provider provider = new Provider();
        List<Order> orders = new ArrayList<>();
        provider.setOrderList(orders);
        assertEquals(orders.size(), provider.getOrderList().size());
    }

}