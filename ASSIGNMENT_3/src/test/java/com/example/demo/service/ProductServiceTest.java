package com.example.demo.service;

import com.example.demo.manager.DateManager;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.path.to.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    DateManager dateManager = new DateManager();

    @Test
    void productInventoryBetween() {

        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";

        List<Inventory> inventoryList = new ArrayList<>();


        Mockito.when(productRepository.productInventoryBetween(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).thenReturn(inventoryList);
        List<Inventory> inventoryActual = productService.productInventoryBetween(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(inventoryActual.size(), inventoryList.size());

    }
}