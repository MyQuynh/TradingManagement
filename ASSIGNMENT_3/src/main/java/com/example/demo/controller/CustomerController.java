package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;

import com.example.demo.model.ReceivingNote;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

    // Search by firstName
    @RequestMapping(value="/customers/searchFirstName", method = RequestMethod.GET)
    public List<Customer> fetchDataByFirstName(@RequestParam String firstname){

        return customerService.findByFirstName(firstname);
    }

    // Search by lastName
    @RequestMapping(value="/customers/searchLastName", method = RequestMethod.GET)
    public List<Customer> fetchDataByLastName(@RequestParam String lastname){
        return customerService.findByLastName(lastname);
    }

    // Search by Address
    @RequestMapping(value="/customers/searchAddress", method = RequestMethod.GET)
    public List<Customer> fetchDataByAddress(@RequestParam String address){
        return customerService.findByAddress(address);
    }

    // Search by Phone
    @RequestMapping(value="/customers/searchPhone", method = RequestMethod.GET)
    public List<Customer> fetchDataByPhone(@RequestParam String phone){
        return customerService.findByPhone(phone);
    }

    // Search by Fax
    @RequestMapping(value="/customers/searchFax", method = RequestMethod.GET)
    public List<Customer> fetchDataByFax(@RequestParam String fax){
        return customerService.findByFax(fax);
    }

    // Search by Email
    @RequestMapping(value="/customers/searchEmail", method = RequestMethod.GET)
    public List<Customer> fetchDataByEmail(@RequestParam String email){
        return customerService.findByEmail(email);
    }

    // Search by Contact
    @RequestMapping(value="/customers/searchContactPerson", method = RequestMethod.GET)
    public List<Customer> fetchDataByContactPerson(@RequestParam String contactPerson){
        return customerService.findByContactPerson(contactPerson);
    }



}