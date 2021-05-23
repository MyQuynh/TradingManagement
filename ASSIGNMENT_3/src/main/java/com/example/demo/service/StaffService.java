package com.example.demo.service;


import com.example.demo.model.Customer;
import com.example.demo.model.SalesInvoice;
import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> findAll() {

        var it = staffRepository.findAll();

        var staffs = new ArrayList<Staff>();
        it.forEach(e -> staffs.add(e));

        return staffs;
    }

    public Staff findStaffById(Long staffId){
        return staffRepository.findStaffById(staffId);
    }


    public Staff save(Staff staff){
        staffRepository.save(staff);
        return staff;
    }

    public void saveAll(List<Staff> staffs){
        staffRepository.saveAll(staffs);
    }

    public String deleteById(Long staffId){
        staffRepository.deleteById(staffId);
        return "SUCESS";
    }

    public boolean existsById(Long staffId){
        return staffRepository.existsById(staffId);
    }

    public Staff updateStaff(Staff staff){
        Staff updateStaff = staffRepository.findStaffById((staff.getId()));
        updateStaff.setFirstName(staff.getFirstName());
        updateStaff.setLastName(staff.getLastName());
        return staffRepository.save(updateStaff);
    }

    // Find by firstname
    public List<Staff> findByFirstName(String firstName){
        return staffRepository.findByFirstName(firstName);
    }

    // Find y lastname
    public List<Staff> findByLastName(String lastName){
        return staffRepository.findByLastName(lastName);
    }

    // Find by address
    public List<Staff> findByAddress(String address){
        return staffRepository.findByAddress(address);
    }

    // Find by email
    public List<Staff> findByEmail(String email){
        return staffRepository.findByEmail(email);
    }

    // Find phone
    public List<Staff> findByPhone(String phone){
        return staffRepository.findByPhone(phone);
    }


}
