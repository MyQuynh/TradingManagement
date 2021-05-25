package com.example.demo.controller;

import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;
import com.path.to.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    // Search by date
    @RequestMapping(value="/products/inventory" , method= RequestMethod.GET)
    public List<Inventory> fetchInventory(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                          @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return productService.productInventoryBetween(startDate, endDate);
    }

    // Find delivery note between date
    @GetMapping("/products1/inventory")
    public ResponseEntity<List<Inventory>> getAllOrderBetweenDate(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Inventory> list = productService.getProductInventoryBetween(startDate,endDate,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Inventory>>(list, new HttpHeaders(), HttpStatus.OK);
    }


}
