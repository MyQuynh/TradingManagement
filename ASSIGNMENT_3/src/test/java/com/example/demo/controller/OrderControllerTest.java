package com.example.demo.controller;

import com.example.demo.manager.DateManager;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.model.Provider;
import com.example.demo.model.Staff;
import com.example.demo.service.DeliveryNoteService;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    ObjectMapper mapper  = new ObjectMapper();

    DateManager dateManager = new DateManager();

    @Test
    void findAll() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Provider provider = new Provider();

        List<Order> orders = IntStream.range(0, 10)
                .mapToObj(i -> new Order(i,sDate1,staff,provider))
                .collect(Collectors.toList());

        given(orderService.findAll()).willReturn(orders);

        mvc.perform(get("/api/v1/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].date").value(sDate1))
                .andExpect(jsonPath("$[0].staff.id").value(staff.getId()))
                .andExpect(jsonPath("$[0].provider.id").value(provider.getId()));
    }

    @Test
    void findOrderById() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Provider provider = new Provider();

        Order order = new Order(0L,sDate1, staff, provider);

        given(orderService.findOrderById(order.getId())).willReturn(order);

        mvc.perform(get("/api/v1/orders/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.provider.id").value(provider.getId()));
    }

    @Test
    void createOrder() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Provider provider = new Provider();

        Order order = new Order(0L,sDate1, staff, provider);

        given(orderService.save(any(Order.class))).willReturn(order);
        mvc.perform(post("/api/v1/orders/add")
                .content(mapper.writeValueAsString(order)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(sDate1)))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.provider.id").value(provider.getId()));

    }

    @Test
    void updateOrder() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();
        Provider provider = new Provider();

        Order order = new Order(0L,sDate1, staff, provider);

        given(orderService.updateOrder(any(Order.class))).willReturn(order);
        mvc.perform(put("/api/v1/orders/{id}", order.getId())
                .content(mapper.writeValueAsString(order)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(sDate1)))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()))
                .andExpect(jsonPath("$.provider.id").value(provider.getId()));
    }

    @Test
    void deleteOrder() throws Exception {
        Mockito.when(orderService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/orders/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDataByDate() throws Exception {
        Date startDate = dateManager.convertStringToDate("2020-02-01");
        Date endDate = dateManager.convertStringToDate("2021-02-01");


        List<Order> orders = IntStream.range(0, 10)
                .mapToObj(i -> new Order(i, dateManager.convertDateToString(dateManager.between(startDate, endDate)), new Staff(), new Provider()))
                .collect(Collectors.toList());


        given(orderService.findDateBetween(startDate,endDate)).willReturn(orders);

        mvc.perform(get("/api/v1/orders/searchByDate").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", dateManager.convertDateToString(startDate))
                .param("endDate", dateManager.convertDateToString(endDate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(orders.size())));
    }
}