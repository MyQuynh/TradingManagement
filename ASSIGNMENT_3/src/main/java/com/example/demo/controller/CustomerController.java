package com.example.demo.controller;

import java.util.List;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;

import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // Get all the customer
    @GetMapping("/customers")
    public List<Customer> findAllCustomer(){
        return customerService.findAll();
    }

    // Get the customer by id
    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return customerService.findCustomersById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the customer
    @PostMapping("/customers/add")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    // Update the customer by id
    @PutMapping("/customers/update/{id}")
    public Customer updateCustomer(@PathVariable("id") long id) throws ResourcesNotFoundException {
        Customer customer = customerService.findCustomersById(id);
        return customerService.updateCustomer(customer);
    }


    // Delete the customer by id
    @DeleteMapping("/customers/delete/{id}")
    public void deleteCustomer(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            Customer customer = customerService.findCustomersById(id);
            customerService.deleteById(id);
        } catch (Exception e){
            System.out.println("The customer id is not in the database yet");
        }

    }


}