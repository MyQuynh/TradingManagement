package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.service.ReceivingNoteService;
import com.example.demo.service.SalesInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SalesInvoiceController {

    @Autowired
    SalesInvoiceService salesInvoiceService;

    // Get all the order
    @GetMapping("/salesInvoices")
    public List<SalesInvoice> findAll(){
        return salesInvoiceService.findAll();
    }

    // Get the order by id
    @GetMapping("/salesInvoices/{id}")
    public SalesInvoice findSalesInvoiceById(@PathVariable("id") long id) throws ResourcesNotFoundException {

        return salesInvoiceService.findSalesInvoiceById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));

    }

    // Create the receiving Note
    @PostMapping("/salesInvoices/add")
    public SalesInvoice createSaleInvoice(@RequestBody SalesInvoice salesInvoice){
        return salesInvoiceService.save(salesInvoice);
    }

    // Update the receiving note  by id
    @PutMapping("/salesInvoices/update/{id}")
    public SalesInvoice updateSaleInvoice(@PathVariable("id") long id) throws ResourcesNotFoundException{
        SalesInvoice salesInvoice = salesInvoiceService.findSalesInvoiceById(id);
//                .orElseThrow(() -> new ResourcesNotFoundException("Not found customer with Id: "+ id));
        return salesInvoiceService.updateSalesInvoice(salesInvoice);
    }


    // Delete the receiving note by id
    @DeleteMapping("/salesInvoices/{id}")
    public void deleteSaleInvoice(@PathVariable("id") long id) throws ResourcesNotFoundException{
        try {
            SalesInvoice salesInvoice = salesInvoiceService.findSalesInvoiceById(id);
            salesInvoiceService.deleteById(id);
        } catch (NullPointerException e){
            System.out.println("The receiving note id is not in the database yet");
        }

    }

    @RequestMapping(value="/salesInvoices/searchByDate/" , method=RequestMethod.GET)
    public  List<SalesInvoice> fetchDataByDate(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return salesInvoiceService.findDateBetween(startDate, endDate);
    }


}
