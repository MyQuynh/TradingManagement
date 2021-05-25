package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
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

    // This is the paging part

    // Find by FirstName
    Page<Customer> findCustomersByFirstName(String firstName, Pageable pageable);

    // Find by Lastname
    Page<Customer> findCustomersByLastName(String lastName, Pageable pageable);

    // Find by email
    Page<Customer> findCustomersByEmail(String lastName, Pageable pageable);

    // Find by address
    Page<Customer> findCustomersByAddress(String address, Pageable pageable);

    // Find by contact person
    Page<Customer> findCustomersByContactPerson(String contactPerson, Pageable pageable);

    // Find by phone
    Page<Customer> findCustomersByPhone(String phone, Pageable pageable);

    // Find by fax
    Page<Customer> findCustomersByFax(String fax, Pageable pageable);

}