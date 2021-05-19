package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {

        var it = customerRepository.findAll();

        var customers = new ArrayList<Customer>();
        it.forEach(e -> customers.add(e));

        return customers;
    }

    public Customer findCustomersById(Long customerId){
        return customerRepository.findCustomersById(customerId);
    }

    public List<Customer> findByFirstName(String customerName){
        return customerRepository.findByFirstName(customerName);
    }

    public Customer save(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    public void saveAll(List<Customer> customers){
        customerRepository.saveAll(customers);
    }

    public void deleteById(Long customerId){
        customerRepository.deleteById(customerId);
    }

    public boolean existsById(Long customerId){
        return customerRepository.existsById(customerId);
    }

    public Customer updateCustomer(Customer customer){
        Customer updateCustomer = customerRepository.findCustomersById(customer.getId());
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        return customerRepository.save(updateCustomer);
    }






}
