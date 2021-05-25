package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryDetail;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.OrderDetail;
import com.example.demo.service.DeliveryNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeliveryNoteController {
    @Autowired
    DeliveryNoteService deliveryNoteService;

    // Get all the customer
    @GetMapping("/deliveryNotes")
    public List<DeliveryNote> findAllDeliveryNotes(){
        return deliveryNoteService.findAllDeliveryNotes();
    }

    // Get the customer by id
    @GetMapping("/deliveryNotes/{id}")
    public DeliveryNote findDeliveryNoteById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return deliveryNoteService.findDeliveryNoteById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the customer
    @PostMapping("/deliveryNotes/add")
    public DeliveryNote createDeliveryNote(@RequestBody DeliveryNote deliveryNote){
        return deliveryNoteService.save(deliveryNote);
    }

    // Update the customer by id
    @PutMapping("/deliveryNotes/update/{id}")
    public DeliveryNote updateDeliveryNote(@PathVariable("id") long id) throws ResourcesNotFoundException{
        DeliveryNote deliveryNote = deliveryNoteService.findDeliveryNoteById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return deliveryNoteService.updateDeliveryNote(deliveryNote);
    }


    // Delete the customer by id
    @DeleteMapping("/deliveryNotes/{id}")
    public void deleteDeliveryNote(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            DeliveryNote deliveryNote = deliveryNoteService.findDeliveryNoteById(id);
            deliveryNoteService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The delivery id is not in the database yet");
        }

    }

    // Search by date
    @RequestMapping(value="/deliveryNotes/searchByDate" , method=RequestMethod.GET)
    public  List<DeliveryNote> fetchDataByDate(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                               @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return deliveryNoteService.findDateBetween(startDate, endDate);
    }

    // Add an delivery detail
    @PutMapping("/deliveryNotes/addDeliveryDetail/{deliveryNoteId}")
    public String addDeliveryDetailToDeliveryNote(@PathVariable Long deliveryNoteId, @RequestBody DeliveryDetail deliveryDetail) throws ResourcesNotFoundException {
        deliveryNoteService.addDeliveryDetailToDeliveryNote(deliveryNoteId, deliveryDetail);
        return "DeliveryDetail has been successfully add to DeliveryNote :: " + deliveryNoteId;
    }

//    // Search by staff id
//    @RequestMapping(value="/deliveryNotes/searchStaff/" , method=RequestMethod.GET)
//    public  List<DeliveryNote> fetchDataByDate(@RequestParam("staff_id") Long staff_id) {
//        return deliveryNoteService.findByStaff(staff_id);
//    }





    // Get list of deliveryNote between start date and end date
//    @RequestMapping("/deliveryNotes/searchbydate/{startDate}-{endDate}")
//    public List<DeliveryNote> fetchDataByLastName(@PathVariable Date startDate, @PathVariable Date endDate){
//        return deliveryNoteService.findAllDeliveryNoteBetween(startDate, endDate);
//    }

//    @GetMapping("/customer/bulkcreate")
//    public String bulkcreate(){
//        // save a single Customer
//        deliveryNoteService.save(new DeliveryNote("2019-21021", 34));
//
//        // save a list of Customers
//        deliveryNoteService.saveAll(Arrays.asList(new DeliveryNote("Salim", "Khan")
//                , new DeliveryNote("Rajesh", "Parihar")
//                , new DeliveryNote("Rahul", "Dravid")
//                , new DeliveryNote("Dharmendra", "Bhojwani")));
//
//        return "DeliveryNote are created";
//    }
//    @PostMapping("/customer/create")
//    public String create(@RequestBody CustomerUI customer){
//        // save a single Customer
//        deliveryNoteService.save(new DeliveryNote(customer.getFirstName(), customer.getLastName()));
//
//        return "Customer is created";
//    }
//    @GetMapping("/customer/findall")
//    public List<CustomerUI> findAll(){
//
//        List<DeliveryNote> deliveryNotes = deliveryNoteService.findAll();
//        List<CustomerUI> customerUI = new ArrayList<>();
//
//        for (DeliveryNote deliveryNote : deliveryNotes) {
//            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
//        }
//
//        return customerUI;
//    }
//
//    @RequestMapping("/customer/search/{id}")
//    public String search(@PathVariable long id){
//        String deliveryNote = "";
//        deliveryNote = deliveryNoteService.findById(id).toString();
//        return deliveryNote;
//    }
//
//    @RequestMapping("/customer/searchbyfirstname/{firstname}")
//    public List<CustomerUI> fetchDataByLastName(@PathVariable String firstname){
//
//        List<Customer> customers = customerService.findByFirstName(firstname);
//        List<CustomerUI> customerUI = new ArrayList<>();
//
//        for (Customer customer : customers) {
//            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
//        }
//
//        return customerUI;
//    }
}
