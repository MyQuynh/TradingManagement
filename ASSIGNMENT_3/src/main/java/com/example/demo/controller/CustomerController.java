package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;

import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) throws ResourcesNotFoundException {
//      Customer customer1 = customerService.findCustomersById(id);
        return customerService.updateCustomer(customer);
    }


    // Delete the customer by id
    @DeleteMapping("/customers/{id}")
    @ResponseBody
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

    // Paging

    // Find all customers
    @GetMapping("/customers1")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomers(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by firstname
    @GetMapping("/customers1/searchFirstName")
    public ResponseEntity<List<Customer>> getAllCustomersByFirstName(
            @RequestParam String firstName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByFirstName(firstName,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by lastname
    @GetMapping("/customers1/searchLastName")
    public ResponseEntity<List<Customer>> getAllCustomersByLastName(
            @RequestParam String lastName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByLastName(lastName,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by address
    @GetMapping("/customers1/searchAddress")
    public ResponseEntity<List<Customer>> getAllCustomersAddress(
            @RequestParam String address,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByAddress(address,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by Phone
    @GetMapping("/customers1/searchPhone")
    public ResponseEntity<List<Customer>> getAllCustomersPhone(
            @RequestParam String phone,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByPhone(phone,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by Fax
    @GetMapping("/customers1/searchFax")
    public ResponseEntity<List<Customer>> getAllCustomersFax(
            @RequestParam String fax,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByFax(fax,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by Email
    @GetMapping("/customers1/searchEmail")
    public ResponseEntity<List<Customer>> getAllCustomersEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByEmail(email,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by Contact
    @GetMapping("/customers1/searchContact")
    public ResponseEntity<List<Customer>> getAllCustomersContact(
            @RequestParam String contact,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Customer> list = customerService.getAllCustomersByContactPerson(contact,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Add an sales invoice
//    @PutMapping("/customers/addSalesInvoice/{customerId}")
//    public String addSalesInvoiceToCustomer(@PathVariable Long customerId, @RequestBody SalesInvoice salesInvoice) throws ResourcesNotFoundException {
//        customerService.addSalesInvoiceToCustomer(customerId, salesInvoice);
//        return "SalesInvoice has been successfully add to Customer :: " + customerId;
//    }


}