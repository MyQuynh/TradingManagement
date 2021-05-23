package com.example.demo.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    // Find list of customer by firstname
    List<Customer> findByFirstName(String firstName);

    // Find list of customer by last name
    List<Customer> findByLastName(String LastName);

    // Find by address
    List<Customer> findCustomersByAddress(String address);

    // Find by contact person
    List<Customer> findCustomersByContactPerson(String contactPerson);

    // Find by phone
    List<Customer> findCustomersByPhone(String phone);

    // Find by fax
    List<Customer> findCustomersByFax(String fax);

    // Find by email
    List<Customer> findCustomersByEmail(String email);

    Customer findCustomersById(Long id);

}