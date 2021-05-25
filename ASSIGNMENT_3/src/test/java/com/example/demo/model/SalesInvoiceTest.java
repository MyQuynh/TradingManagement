package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesInvoiceTest {

    @Test
    void getAndSetId() {
        Long salesInvoiceId = 0L;
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setId(salesInvoiceId);
        assertEquals(salesInvoiceId, salesInvoice.getId());
    }


    @Test
    void getAndSetDate() {
        String date = "2021-01-01";
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setDate(date);
        assertEquals(date, salesInvoice.getDate());
    }


    @Test
    void getAndSetStaff() {
        Staff staff = new Staff();
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setStaff(staff);
        assertEquals(staff, salesInvoice.getStaff());
    }


    @Test
    void getAndSetCustomer() {
        Customer customer = new Customer();
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setCustomer(customer);
        assertEquals(customer, salesInvoice.getCustomer());
    }

    @Test
    void getAndSetSaleDetailList() {
        List<SaleDetail> saleDetailTestList = new ArrayList<>();
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setSaleDetails(saleDetailTestList);
        assertEquals(saleDetailTestList.size(), salesInvoice.getSaleDetails().size());
    }


    @Test
    void getAndSerTotal_value() {
        float totalValue = 0;
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setTotal_value(totalValue);
        assertEquals(totalValue, salesInvoice.getTotal_value());
    }

}