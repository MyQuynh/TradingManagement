package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.ReceivingNoteService;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StaffController {
    @Autowired
    StaffService staffService;

    // Get all the order
    @GetMapping("staffs")
    public List<Staff> findAll(){
        return staffService.findAll();
    }

    // Get the order by id
    @GetMapping("staffs/{id}")
    public Staff findStaffById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return staffService.findStaffById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the receiving Note
    @PostMapping("staffs/add")
    public Staff createStaff(@RequestBody Staff staff){
        return staffService.save(staff);
    }

    // Update the receiving note  by id
    @PutMapping("staffs/update/{id}")
    public Staff updateStaff(@PathVariable("id") long id) throws ResourcesNotFoundException{
        Staff staff = staffService.findStaffById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return staffService.updateStaff(staff);
    }


    // Delete the receiving note by id
    @DeleteMapping("receivingNotes/delete/{id}")
    public void deleteStaff(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
//            Staff staff = staffService.findStaffById(id);
            staffService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The receiving note id is not in the database yet");
        }

    }

//    @GetMapping("/bulkcreate")
//    public String bulkcreate(){
//// save a single Customer
//        staffRepository.save(new Staff("Rajesh", "Bhojwani"));
//
//// save a list of Customers
//        staffRepository.saveAll(Arrays.asList(new Staff("Salim", "Khan")
//                , new Staff("Rajesh", "Parihar")
//                , new Staff("Rahul", "Dravid")
//                , new Staff("Dharmendra", "Bhojwani")));
//
//        return "Customers are created";
//    }
//    @PostMapping("/create")
//    public String create(@RequestBody Staff staff){
//// save a single Customer
//        staffRepository.save(new Staff(staff.getFirstName(), staff.getLastName()));
//
//        return "Customer is created";
//    }
//    @GetMapping("/findall")
//    public List<Staff> findAll(){
//
//        List<Staff> staffs = staffRepository.findAll();
//        List<Staff> staffList = new ArrayList<>();
//
//        for (Staff staff : staffs) {
//            staffList.add(new Staff(staff.getFirstName(),staff.getLastName()));
//        }
//
//        return staffList;
//    }
//
//    @RequestMapping("/search/{id}")
//    public String search(@PathVariable long id){
//        String staff = "";
//        staff = staffRepository.findById(id).toString();
//        return staff;
//    }
//
//    @RequestMapping("/searchbyfirstname/{firstname}")
//    public List<Staff> fetchDataByFirstName(@PathVariable String firstname){
//
//        List<Staff> staffs = staffRepository.findByFirstName(firstname);
//        List<Staff> staffList = new ArrayList<>();
//        for (Staff staff : staffs) {
//            staffList.add(new Staff(staff.getFirstName(),staff.getLastName()));
//        }
//        return staffList;
//    }
}