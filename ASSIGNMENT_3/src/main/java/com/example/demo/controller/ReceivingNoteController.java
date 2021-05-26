package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.ReceivingDetail;
import com.example.demo.model.ReceivingNote;
import com.example.demo.service.ReceivingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReceivingNoteController {

    @Autowired
    ReceivingNoteService receivingNoteService;

    // Get all the order
    @GetMapping("/receivingNotes")
    public List<ReceivingNote> findAll(){
        return receivingNoteService.findAll();
    }

    // Get the order by id
    @GetMapping("/receivingNotes/{id}")
    public ReceivingNote findReceivingNoteById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return receivingNoteService.findReceivingNoteById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the receiving Note
    @PostMapping("/receivingNotes/add")
    public ReceivingNote createReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.save(receivingNote);
    }

    // Update the receiving note  by id
    @PutMapping("/receivingNotes/{id}")
    public ReceivingNote updateReceivingNote(@PathVariable("id") long id, @RequestBody ReceivingNote receivingNote) throws ResourcesNotFoundException{
//        ReceivingNote receivingNote = receivingNoteService.findReceivingNoteById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return receivingNoteService.updateReceivingNote(receivingNote);
    }


    // Delete the receiving note by id
    @DeleteMapping("/receivingNotes/{id}")
    public void deleteReceivingNote(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            ReceivingNote receivingNote = receivingNoteService.findReceivingNoteById(id);
            receivingNoteService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The receiving note id is not in the database yet");
        }

    }

    @RequestMapping(value="/receivingNotes/searchByDate" , method=RequestMethod.GET)
    public  List<ReceivingNote> fetchDataByDate(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                        @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return receivingNoteService.findDateBetween(startDate, endDate);
    }

    // Add an receiving detail
    @PutMapping("/receivingNotes/addReceivingDetail/{receivingNoteId}")
    public String addReceivingDetailToReceivingNote(@PathVariable Long receivingNoteId, @RequestBody ReceivingDetail receivingDetail) throws ResourcesNotFoundException {
        receivingNoteService.addReceivingDetailToReceivingNote(receivingNoteId, receivingDetail);
        return "ReceivingDetail has been successfully add to ReceivingNote :: " + receivingNoteId;
    }

//    // Find by staff
//    @RequestMapping(value="/receivingNotes/searchByStaff/" , method=RequestMethod.GET)
//    public  List<ReceivingNote> fetchDataByStaff(@RequestParam("staff_id") Long staff_id) {
//        return receivingNoteService.findByStaff(staff_id);
//    }

    // Paging
    // Find all the receiving note
    @GetMapping("/receivingNotes1")
    public ResponseEntity<List<ReceivingNote>> getAllReceivingNotes(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<ReceivingNote> list = receivingNoteService.getAllReceivingNote(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<ReceivingNote>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find delivery note between date
    @GetMapping("/receivingNote1/searchByDate")
    public ResponseEntity<List<ReceivingNote>> getAllReceivingNoteBetweenDate(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<ReceivingNote> list = receivingNoteService.getAllReceivingNoteBetween(startDate,endDate,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<ReceivingNote>>(list, new HttpHeaders(), HttpStatus.OK);
    }


}
