package com.example.demo.controller;

import com.example.demo.manager.DateManager;
import com.example.demo.model.Order;
import com.example.demo.model.Provider;
import com.example.demo.model.Staff;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.path.to.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
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
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    ObjectMapper mapper  = new ObjectMapper();

    DateManager dateManager = new DateManager();

    @Test
    void fetchInventory() throws Exception {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        List<Inventory> inventoryLists = new ArrayList<>();

        given(productService.productInventoryBetween(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).willReturn(inventoryLists);

        mvc.perform(get("/api/v1/products/inventory").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}