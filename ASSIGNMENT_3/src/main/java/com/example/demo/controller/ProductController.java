package com.example.demo.controller;

import com.example.demo.model.DeliveryNote;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import com.path.to.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    // Search by date
    @RequestMapping(value="/products/inventory/" , method= RequestMethod.GET)
    public List<Inventory> fetchInventory(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                          @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return productService.productInventoryBetween(startDate, endDate);
    }


}
