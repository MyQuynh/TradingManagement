package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.ReceivingNote;
import com.example.demo.service.ReceivingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalesInvoiceController {

    @Autowired
    ReceivingNoteService receivingNoteService;

    // Get all the order
    @GetMapping("salesInvoices")
    public List<ReceivingNote> findAll(){
        return receivingNoteService.findAll();
    }

    // Get the order by id
    @GetMapping("salesInvoices/{id}")
    public ReceivingNote findReceivingNoteById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return receivingNoteService.findReceivingNoteById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the receiving Note
    @PostMapping("salesInvoices/add")
    public ReceivingNote createReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.save(receivingNote);
    }

    // Update the receiving note  by id
    @PutMapping("salesInvoices/update/{id}")
    public ReceivingNote updateReceivingNote(@PathVariable("id") long id) throws ResourcesNotFoundException{
        ReceivingNote receivingNote = receivingNoteService.findReceivingNoteById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return receivingNoteService.updateReceivingNote(receivingNote);
    }


    // Delete the receiving note by id
    @DeleteMapping("salesInvoices/delete/{id}")
    public void deleteReceivingNote(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            ReceivingNote receivingNote = receivingNoteService.findReceivingNoteById(id);
            receivingNoteService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The receiving note id is not in the database yet");
        }

    }


}
