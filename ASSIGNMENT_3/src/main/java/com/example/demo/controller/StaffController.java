package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StaffController {
    @Autowired
    StaffRepository staffRepository;

    @GetMapping("/bulkcreate")
    public String bulkcreate(){
// save a single Customer
        staffRepository.save(new Staff("Rajesh", "Bhojwani"));

// save a list of Customers
        staffRepository.saveAll(Arrays.asList(new Staff("Salim", "Khan")
                , new Staff("Rajesh", "Parihar")
                , new Staff("Rahul", "Dravid")
                , new Staff("Dharmendra", "Bhojwani")));

        return "Customers are created";
    }
    @PostMapping("/create")
    public String create(@RequestBody Staff staff){
// save a single Customer
        staffRepository.save(new Staff(staff.getFirstName(), staff.getLastName()));

        return "Customer is created";
    }
    @GetMapping("/findall")
    public List<Staff> findAll(){

        List<Staff> staffs = staffRepository.findAll();
        List<Staff> staffList = new ArrayList<>();

        for (Staff staff : staffs) {
            staffList.add(new Staff(staff.getFirstName(),staff.getLastName()));
        }

        return staffList;
    }

    @RequestMapping("/search/{id}")
    public String search(@PathVariable long id){
        String staff = "";
        staff = staffRepository.findById(id).toString();
        return staff;
    }

    @RequestMapping("/searchbyfirstname/{firstname}")
    public List<Staff> fetchDataByFirstName(@PathVariable String firstname){

        List<Staff> staffs = staffRepository.findByFirstName(firstname);
        List<Staff> staffList = new ArrayList<>();
        for (Staff staff : staffs) {
            staffList.add(new Staff(staff.getFirstName(),staff.getLastName()));
        }
        return staffList;
    }
}