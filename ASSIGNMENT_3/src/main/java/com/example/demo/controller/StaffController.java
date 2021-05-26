package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.model.Staff;
import com.example.demo.repository.StaffRepository;
import com.example.demo.service.ReceivingNoteService;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StaffController {
    @Autowired
    StaffService staffService;

    // Get all the order
    @GetMapping("/staffs")
    public List<Staff> findAll(){
        return staffService.findAll();
    }

    // Get the order by id
    @GetMapping("/staffs/{id}")
    public Staff findStaffById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return staffService.findStaffById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the receiving Note
    @PostMapping("/staffs/add")
    public Staff createStaff(@RequestBody Staff staff){
        return staffService.save(staff);
    }

    // Update the receiving note  by id
    @PutMapping("/staffs/{id}")
    public Staff updateStaff(@PathVariable("id") long id, @RequestBody Staff staff) throws ResourcesNotFoundException{
//        Staff staff = staffService.findStaffById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return staffService.updateStaff(staff);
    }

    // TODO: cHANGING ALL TO STAFFS/{ID}
    // Delete the receiving note by id
    @DeleteMapping("/staffs/{id}")
    public void deleteStaff(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
//            Staff staff = staffService.findStaffById(id);
            staffService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The receiving note id is not in the database yet");
        }

    }

    // Search by firstName
    @RequestMapping(value="/staffs/searchFirstName", method = RequestMethod.GET)
    public List<Staff> fetchDataByFirstName(@RequestParam String firstname){

        return staffService.findByFirstName(firstname);
    }

    // Search by lastName
    @RequestMapping(value="/staffs/searchLastName", method = RequestMethod.GET)
    public List<Staff> fetchDataByLastName(@RequestParam String lastname){
        return staffService.findByLastName(lastname);
    }

    // Search by Address
    @RequestMapping(value="/staffs/searchAddress", method = RequestMethod.GET)
    public List<Staff> fetchDataByAddress(@RequestParam String address){
        return staffService.findByAddress(address);
    }

    // Search by Phone
    @RequestMapping(value="/staffs/searchPhone", method = RequestMethod.GET)
    public List<Staff> fetchDataByPhone(@RequestParam String phone){
        return staffService.findByPhone(phone);
    }

    // Search by Email
    @RequestMapping(value="/staffs/searchEmail", method = RequestMethod.GET)
    public List<Staff> fetchDataByEmail(@RequestParam String email){
        return staffService.findByEmail(email);
    }


    // Paging

    // Find all customers
    @GetMapping("/staffs1")
    public ResponseEntity<List<Staff>> getAllStaffs(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Staff> list = staffService.getAllStaffs(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Staff>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by firstname
    @GetMapping("/staffs1/searchFirstName")
    public ResponseEntity<List<Staff>> getAllStaffsByFirstName(
            @RequestParam String firstName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Staff> list = staffService.getAllStaffsByFirstName(firstName,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Staff>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by lastname
    @GetMapping("/staffs1/searchLastName")
    public ResponseEntity<List<Staff>> getAllStaffsByLastName(
            @RequestParam String lastName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Staff> list = staffService.getAllStaffsByLastName(lastName,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Staff>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by address
    @GetMapping("/staffs1/searchAddress")
    public ResponseEntity<List<Staff>> getAllStaffsAddress(
            @RequestParam String address,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Staff> list = staffService.getAllStaffsByAddress(address,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Staff>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by Phone
    @GetMapping("/staffs1/searchPhone")
    public ResponseEntity<List<Staff>> getAllStaffsPhone(
            @RequestParam String phone,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Staff> list = staffService.getAllStaffsByPhone(phone,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Staff>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find customer by Email
    @GetMapping("/staffs1/searchEmail")
    public ResponseEntity<List<Staff>> getAllStaffsEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Staff> list = staffService.getAllStaffsByEmail(email,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Staff>>(list, new HttpHeaders(), HttpStatus.OK);
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