package com.example.demo.service;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Customer updateCustomer(Customer customer) throws ResourcesNotFoundException {
        Customer updateCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ customer.getId()));
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());

//        if(updateCustomer.isPresent()) {
//            Customer existingCustomer = updateCustomer.get();
//            existingCustomer.setFirstName(customer.getFirstName());
//            existingCustomer.setLastName(customer.getLastName());
//        } else {
//            return null;
//        }

        return customerRepository.save(updateCustomer);
    }






}
