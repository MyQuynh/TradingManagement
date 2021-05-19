package com.example.demo.repository;

import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAll();

    Staff findStaffById(Long staffId);
}
