package com.example.demo.service;

import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.path.to.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Inventory> productInventoryBetween(Date startDate, Date endDate){
        return productRepository.productInventoryBetween(startDate, endDate);
    }

}
