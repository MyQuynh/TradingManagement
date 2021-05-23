package com.example.demo.controller;

import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.model.Provider;
import com.example.demo.model.Staff;
import com.example.demo.service.DeliveryNoteService;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void findOrderById() {

    }

    @Test
    void createOrder() {

    }

    @Test
    void updateOrder() {

    }

    @Test
    void deleteOrder() {

    }

    @Test
    void fetchDataByDate() {

    }
}