package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    // Main functions
    List<Staff> findAll();

    List<Staff> findByFirstName(String FirstName);

    List<Staff> findByLastName(String LastName);
    List<Staff> findByAddress(String address);
    List<Staff> findByEmail(String email);
    List<Staff> findByPhone(String phone);

    Staff findStaffById(Long staffId);

    // Paging
    Page<Staff> findAll(Pageable pageable);

    Page<Staff> findByFirstName(String FirstName, Pageable pageable);

    Page<Staff> findByLastName(String LastName, Pageable pageable);
    Page<Staff> findByAddress(String address, Pageable pageable);
    Page<Staff> findByEmail(String address, Pageable pageable);
    Page<Staff> findByPhone(String phone, Pageable pageable);
}
