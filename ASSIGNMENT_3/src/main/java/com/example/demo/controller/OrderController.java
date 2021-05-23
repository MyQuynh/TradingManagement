package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.SalesInvoice;
import com.example.demo.service.DeliveryNoteService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {


    @Autowired
    OrderService orderService;

    // Get all the customer
    @GetMapping("/orders")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    // Get the customer by id
    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return orderService.findOrderById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the customer
    @PostMapping("/orders/add")
    public Order createOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    // Update the customer by id
    @PutMapping("/orders/update/{id}")
    public Order updateOrder(@PathVariable("id") long id) throws ResourcesNotFoundException{
        Order order = orderService.findOrderById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return orderService.updateOrder(order);
    }


    // Delete the customer by id
    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            Order order = orderService.findOrderById(id);
            orderService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The order id is not in the database yet");
        }

    }

    @RequestMapping(value="/orders/searchByDate/" , method=RequestMethod.GET)
    public  List<Order> fetchDataByDate(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                               @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return orderService.findDateBetween(startDate, endDate);
    }

    // Add an order detail
    @PutMapping("/orders/addOrderDetail/{orderId}")
    public String addOrderDetailToOrder(@PathVariable Long orderId, @RequestBody OrderDetail orderDetail) throws ResourcesNotFoundException {
        orderService.addOrderDetailToOrder(orderId, orderDetail);
        return "OrderDetail has been successfully add to Order :: " + orderId;
    }

//    // Find by staff
//    @RequestMapping(value="/orders/searchByStaff/" , method=RequestMethod.GET)
//    public  List<Order> fetchDataByStaff(@RequestParam("staff_id") Long staff_id) {
//        return orderService.findByStaff(staff_id);
//    }
//
//    // Find by provider
//    @RequestMapping(value="/orders/searchByProvider/" , method=RequestMethod.GET)
//    public  List<Order> fetchDataByProvider(@RequestParam("provider_id") Long provider_id) {
//        return orderService.findByProvider(provider_id);
//    }


}
