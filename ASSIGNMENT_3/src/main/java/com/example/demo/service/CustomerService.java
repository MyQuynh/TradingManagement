package com.example.demo.service;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.SalesInvoice;
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

        return customerRepository.findAll();
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

    public String deleteById(Long customerId){

        customerRepository.deleteById(customerId);
        return "SUCCESS";
    }

    public boolean existsById(Long customerId){
        return customerRepository.existsById(customerId);
    }

    public Customer updateCustomer(Customer customer) throws ResourcesNotFoundException {
        Customer updateCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ customer.getId()));
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setAddress(customer.getAddress());
        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setFax(customer.getFax());
        updateCustomer.setContactPerson(customer.getContactPerson());

//        if(updateCustomer.isPresent()) {
//            Customer existingCustomer = updateCustomer.get();
//            existingCustomer.setFirstName(customer.getFirstName());
//            existingCustomer.setLastName(customer.getLastName());
//        } else {
//            return null;
//        }

        return customerRepository.save(updateCustomer);
    }

    // Find by firstname
    public List<Customer> findByFirstName(String firstName){
        return customerRepository.findByFirstName(firstName);
    }

    // Find y lastname
    public List<Customer> findByLastName(String lastName){
        return customerRepository.findByLastName(lastName);
    }

    // Find by address
    public List<Customer> findByAddress(String address){
        return customerRepository.findCustomersByAddress(address);
    }

    // Find by phone
    public List<Customer> findByPhone(String phone){
        return customerRepository.findCustomersByPhone(phone);
    }

    // Find by fax
    public List<Customer> findByFax(String fax){
        return customerRepository.findCustomersByFax(fax);
    }

    // Find by email
    public List<Customer> findByEmail(String email){
        return customerRepository.findCustomersByEmail(email);
    }

    // Find by contact person
    public List<Customer> findByContactPerson(String contactPerson){
        return customerRepository.findCustomersByContactPerson(contactPerson);
    }

    // Add a sale invoice
    public void addSalesInvoiceToCustomer(Long customerId, SalesInvoice salesInvoices) throws ResourcesNotFoundException {
        //LOG.info("CourseId :: {} , Student :: {}", courseId, students);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new ResourcesNotFoundException("Failed to add SalesInvoice. Invalid CustomerId :: " + customerId);
        }
        Customer customer = customerOptional.get();
        List<SalesInvoice> salesInvoices1 = customer.getSalesInvoiceList();
        salesInvoices1.add(salesInvoices);
        customer.setSalesInvoiceList(salesInvoices1);
        customerRepository.save(customer);

    }

}
