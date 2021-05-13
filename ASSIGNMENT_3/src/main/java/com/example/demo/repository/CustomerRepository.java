package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findByFirstName(String FirstName);
    List<Customer> findAll();
}