package com.example.demo.controller;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.example.demo.service.ReceivingNoteService;
import com.example.demo.service.SalesInvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class SalesInvoiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SalesInvoiceService salesInvoiceService;

    ObjectMapper mapper  = new ObjectMapper();

    DateManager dateManager = new DateManager();

    @Test
    void findAll() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Customer customer = new Customer();

        List<SalesInvoice> salesInvoices = IntStream.range(0, 10)
                .mapToObj(i -> new SalesInvoice(i,sDate1,staff,customer,i))
                .collect(Collectors.toList());

        given(salesInvoiceService.findAll()).willReturn(salesInvoices);

        mvc.perform(get("/api/v1/salesInvoices").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].date").value(sDate1))
                .andExpect(jsonPath("$[0].staff.id").value(staff.getId()))
                .andExpect(jsonPath("$[0].customer.id").value(customer.getId()))
                .andExpect(jsonPath("$[0].total_value.id").value(0));
    }

    @Test
    void findSalesInvoiceById() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Customer customer = new Customer();

        SalesInvoice salesInvoice = new SalesInvoice(0L,sDate1,staff,customer,0);

        given(salesInvoiceService.findSalesInvoiceById(salesInvoice.getId())).willReturn(salesInvoice);

        mvc.perform(get("/api/v1/salesInvoices/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].date").value(sDate1))
                .andExpect(jsonPath("$[0].staff.id").value(staff.getId()))
                .andExpect(jsonPath("$[0].customer.id").value(customer.getId()))
                .andExpect(jsonPath("$[0].total_value.id").value(0));
    }

    @Test
    void createSaleInvoice() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Customer customer = new Customer();

        SalesInvoice salesInvoice = new SalesInvoice(0L,sDate1,staff,customer,0);

        given(salesInvoiceService.save(salesInvoice)).willReturn(salesInvoice);

        mvc.perform(get("/api/v1/salesInvoices/add").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.customer.id").value(customer.getId()))
                .andExpect(jsonPath("$.total_value.id").value(0));
    }

    @Test
    void updateSaleInvoice() {
    }

    @Test
    void deleteSaleInvoice() throws Exception {
        Mockito.when(salesInvoiceService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/salesInvoices/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    // TODO: CHANGING THE DATETIME
    void fetchDataByDate() throws Exception {
        Date startDate = dateManager.convertStringToDate("2020-02-01");
        Date endDate = dateManager.convertStringToDate("2021-02-01");


        List<SalesInvoice> salesInvoices = IntStream.range(0, 10)
                .mapToObj(i -> new SalesInvoice(i, dateManager.convertDateToString(dateManager.between(startDate, endDate)), new Staff(), new Customer(), i))
                .collect(Collectors.toList());


        given(salesInvoiceService.findDateBetween(startDate,endDate)).willReturn(salesInvoices);

        mvc.perform(get("/api/v1/salesInvoices/searchByDate/").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", dateManager.convertDateToString(startDate))
                .param("endDate", dateManager.convertDateToString(endDate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(salesInvoices.size())));
    }
}