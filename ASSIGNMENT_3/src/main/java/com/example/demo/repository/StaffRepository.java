package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAll();

    List<Staff> findByFirstName(String FirstName);
    List<Staff> findByLastName(String LastName);
    List<Staff> findByAddress(String address);
    List<Staff> findByEmail(String address);
    List<Staff> findByPhone(String phone);

    Staff findStaffById(Long staffId);
}
