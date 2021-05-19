package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerUI;
import com.example.demo.repository.CustomerRepository;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // Get all the customer
    @GetMapping("customers")
    public List<Customer> findAllCustomer(){
        return customerService.findAll();
    }

    // Get the customer by id
    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return customerService.findCustomersById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the customer
    @PostMapping("customers/add")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    // Update the customer by id
    @PutMapping("customers/update/{id}")
    public Customer updateCustomer(@PathVariable("id") long id) throws ResourcesNotFoundException {
        Customer customer = customerService.findCustomersById(id);
        return customerService.updateCustomer(customer);
    }


    // Delete the customer by id
    @DeleteMapping("customers/delete/{id}")
    public void deleteCustomer(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            Customer customer = customerService.findCustomersById(id);
            customerService.deleteById(id);
        } catch (Exception e){
            System.out.println("The customer id is not in the database yet");
        }

    }


}