package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    List<Customer> findByFirstName(String FirstName);
    List<Customer> findAll();

    Customer findCustomersById(Long id);

}