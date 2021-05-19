package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.service.DeliveryNoteService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {


    @Autowired
    OrderService orderService;

    // Get all the customer
    @GetMapping("/orderNotes")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    // Get the customer by id
    @GetMapping("/deliveryNotes/{id}")
    public Order findOrderById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return orderService.findOrderById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the customer
    @PostMapping("/deliveryNotes/add")
    public Order createOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    // Update the customer by id
    @PostMapping("/deliveryNotes/update/{id}")
    public Order updateOrder(@PathVariable("id") long id) throws ResourcesNotFoundException{
        Order order = orderService.findOrderById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return orderService.updateOrder(order);
    }


    // Delete the customer by id
    @PostMapping("/deliveryNotes/delete/{id}")
    public void deleteOrder(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            Order order = orderService.findOrderById(id);
            orderService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The order id is not in the database yet");
        }

    }


}