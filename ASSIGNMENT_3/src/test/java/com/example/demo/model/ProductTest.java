package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getAndSetId() {
        Long productId = 0L;
        Product product = new Product();
        product.setId(productId);
        assertEquals(productId, product.getId());

    }


    @Test
    void getAndSetName() {
        String productName = "Test";
        Product product = new Product();
        product.setName(productName);
        assertEquals(productName, product.getName());
    }


    @Test
    void getAndSetModel() {
        String productModel = "Test";
        Product product = new Product();
        product.setModel(productModel);
        assertEquals(productModel, product.getModel());
    }


    @Test
    void getAndSetBrand() {
        String productBrand = "Test";
        Product product = new Product();
        product.setBrand(productBrand);
        assertEquals(productBrand, product.getBrand());
    }


    @Test
    void getAndSetCompany() {
        String productCompany = "Test";
        Product product = new Product();
        product.setCompany(productCompany);
        assertEquals(productCompany, product.getCompany());
    }


    @Test
    void getAndSetDescription() {
        String productDescription = "Test";
        Product product = new Product();
        product.setDescription(productDescription);
        assertEquals(productDescription, product.getDescription());
    }


    @Test
    void getAndSetQuantity() {
        int productQuantity = 12;
        Product product = new Product();
        product.setQuantity(productQuantity);
        assertEquals(productQuantity, product.getQuantity());
    }


    @Test
    void getAndSetPrice() {
        int productPrice = 12;
        Product product = new Product();
        product.setPrice(productPrice);
        assertEquals(productPrice, product.getPrice());
    }


    @Test
    void getAndSetCategory() {
        Category category = new Category();
        Product product = new Product();
        product.setCategory(category);
        assertEquals(product.getCategory(), category);
    }


    @Test
    void getAndSetReceivingDetails() {
        List<ReceivingDetail> receivingDetails = new ArrayList<>();
        Product product = new Product();
        product.setReceivingDetails(receivingDetails);
        assertEquals(product.getReceivingDetails().size(), receivingDetails.size());
    }


    @Test
    void getAndSetDeliveryDetails() {
        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        Product product = new Product();
        product.setDeliveryDetails(deliveryDetails);
        assertEquals(deliveryDetails.size(),product.getDeliveryDetails().size());
    }


    @Test
    void getAndSetSaleDetails() {
        List<SaleDetail> saleDetails = new ArrayList<>();
        Product product = new Product();
        product.setSaleDetails(saleDetails);
        assertEquals(saleDetails.size(), product.getSaleDetails().size());
    }

}