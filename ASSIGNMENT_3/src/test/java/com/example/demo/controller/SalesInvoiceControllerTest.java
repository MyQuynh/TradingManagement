package com.example.demo.controller;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.example.demo.service.ReceivingNoteService;
import com.example.demo.service.SalesInvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.path.to.Inventory;
import com.path.to.RevenueCustomer;
import com.path.to.RevenueStaff;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$[0].total_value").value(0));
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
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.customer.id").value(customer.getId()))
                .andExpect(jsonPath("$.total_value").value(0));
    }

    @Test
    void createSaleInvoice() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Customer customer = new Customer();

        SalesInvoice salesInvoice = new SalesInvoice(0L,sDate1,staff,customer,0);

        given(salesInvoiceService.save(any(SalesInvoice.class))).willReturn(salesInvoice);

        mvc.perform(post("/api/v1/salesInvoices/add")
                .content(mapper.writeValueAsString(salesInvoice)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.customer.id").value(customer.getId()))
                .andExpect(jsonPath("$.total_value").value(0));
    }

    @Test
    void updateSaleInvoice() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Customer customer = new Customer();

        SalesInvoice salesInvoice = new SalesInvoice(0L,sDate1,staff,customer,0);

        given(salesInvoiceService.updateSalesInvoice(any(SalesInvoice.class))).willReturn(salesInvoice);

        mvc.perform(put("/api/v1/salesInvoices/{id}", salesInvoice.getId())
                .content(mapper.writeValueAsString(salesInvoice)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.customer.id").value(customer.getId()))
                .andExpect(jsonPath("$.total_value").value(0));
    }

    @Test
    void deleteSaleInvoice() throws Exception {
        Mockito.when(salesInvoiceService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/salesInvoices/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDataByDate() throws Exception {
        Date startDate = dateManager.convertStringToDate("2020-02-01");
        Date endDate = dateManager.convertStringToDate("2021-02-01");


        List<SalesInvoice> salesInvoices = IntStream.range(0, 10)
                .mapToObj(i -> new SalesInvoice(i, dateManager.convertDateToString(dateManager.between(startDate, endDate)), new Staff(), new Customer(), i))
                .collect(Collectors.toList());


        given(salesInvoiceService.findDateBetween(startDate,endDate)).willReturn(salesInvoices);

        mvc.perform(get("/api/v1/salesInvoices/searchByDate").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", dateManager.convertDateToString(startDate))
                .param("endDate", dateManager.convertDateToString(endDate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(salesInvoices.size())));
    }

    @Test
    void fetchRevenueByCustomer() throws Exception {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        List<RevenueCustomer> revenueCustomers = new ArrayList<>();

        given(salesInvoiceService.revenueCustomers(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).willReturn(revenueCustomers);

        mvc.perform(get("/api/v1/salesInvoices/revenueByCustomer").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void fetchRevenueByStaff() throws Exception {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        List<RevenueStaff> revenueStaffs = new ArrayList<>();

        given(salesInvoiceService.revenueStaffs(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).willReturn(revenueStaffs);

        mvc.perform(get("/api/v1/salesInvoices/revenueByStaff").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void fetchTotalRevenue() throws Exception {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        int revenue = 0;

        given(salesInvoiceService.totalRevenue(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).willReturn((float) revenue);

        mvc.perform(get("/api/v1/salesInvoices/totalRevenue").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(jsonPath("$").value((float)revenue));
    }

    @Test
    void fetchRevenueByACustomer() throws Exception {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        Customer customer = new Customer();
        RevenueCustomer1 revenueCustomer1 = new RevenueCustomer1(customer.getId(), 200);

        given(salesInvoiceService.revenueByACustomer(customer.getId(), dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).willReturn(revenueCustomer1);

        mvc.perform(get("/api/v1/salesInvoices/revenueByACustomer").contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(customer.getId()))
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(revenueCustomer1.getId()))
                .andExpect(jsonPath("$.revenue").value(revenueCustomer1.getRevenue()));
    }

    @Test
    void fetchRevenueByAStaff() throws Exception {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        Staff staff = new Staff();
        RevenueStaff1 revenueStaff1 = new RevenueStaff1(staff.getId(), (float) 200);


        given(salesInvoiceService.revenueByAStaff(staff.getId(),dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).willReturn(revenueStaff1);

        mvc.perform(get("/api/v1/salesInvoices/revenueByAStaff").contentType(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(staff.getId()))
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(revenueStaff1.getId()))
                .andExpect(jsonPath("$.revenue").value(revenueStaff1.getRevenue()));
    }
}