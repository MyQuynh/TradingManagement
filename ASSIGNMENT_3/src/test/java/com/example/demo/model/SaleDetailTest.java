package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleDetailTest {

    @Test
    void getAndSetId() {
        Long saleDetailId = 0L;
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(saleDetailId);
        assertEquals(saleDetailId, saleDetail.getId());

    }


    @Test
    void getAndSetQuantity() {
        int quantity = 0;
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setQuantity(quantity);
        assertEquals(saleDetail.getQuantity(), quantity);
    }


    @Test
    void getAndSetPrice() {
        float price = 0;
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setPrice(price);
        assertEquals(saleDetail.getPrice(), price);
    }


    @Test
    void getAndSetSalesInvoice() {
        SalesInvoice salesInvoice = new SalesInvoice();
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setSalesInvoice(salesInvoice);
        assertEquals(saleDetail.getSalesInvoice(), salesInvoice);
    }


    @Test
    void getAndSetProduct() {
        Product product = new Product();
        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setProduct(product);
        assertEquals(saleDetail.getProduct(), product);
    }

}