package com.example.demo.controller;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.service.ReceivingNoteService;
import com.example.demo.service.SalesInvoiceService;
import com.path.to.Revenue;
import com.path.to.RevenueCustomer;
import com.path.to.RevenueStaff;
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
    @PutMapping("/salesInvoices/{id}")
    public SalesInvoice updateSaleInvoice(@PathVariable("id") long id, @RequestBody SalesInvoice salesInvoice) throws ResourcesNotFoundException{
//        SalesInvoice salesInvoice = salesInvoiceService.findSalesInvoiceById(id);
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

    @RequestMapping(value="/salesInvoices/searchByDate" , method=RequestMethod.GET)
    public  List<SalesInvoice> fetchDataByDate(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return salesInvoiceService.findDateBetween(startDate, endDate);
    }

    @RequestMapping(value="/salesInvoices/revenueByCustomer" , method=RequestMethod.GET)
    public  List<RevenueCustomer> fetchRevenueByCustomer(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return salesInvoiceService.revenueCustomer(startDate, endDate);
    }

    @RequestMapping(value="/salesInvoices/revenueByStaff" , method=RequestMethod.GET)
    public  List<RevenueStaff> fetchRevenueByStaff(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return salesInvoiceService.revenueStaff(startDate, endDate);
    }

    @RequestMapping(value="/salesInvoices/totalRevenue" , method=RequestMethod.GET)
    public Float fetchTotalRevenue(@RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                     @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
        return salesInvoiceService.totalRevenue(startDate, endDate);
    }

    // Paging
    // Find all the sale invoice
    @GetMapping("/saleInvoices1")
    public ResponseEntity<List<SalesInvoice>> getAllSaleInvoices(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<SalesInvoice> list = salesInvoiceService.getAllSaleInvoices(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<SalesInvoice>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    // Find sale invoice between date
    @GetMapping("/saleInvoices1/searchByDate")
    public ResponseEntity<List<SalesInvoice>> getAllSaleInvoicesBetweenDate(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<SalesInvoice> list = salesInvoiceService.getAllReceivingNoteBetween(startDate,endDate,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<SalesInvoice>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/saleInvoices1/revenueByCustomer")
    public ResponseEntity<List<RevenueCustomer>> getAllRevenueByCustomer(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<RevenueCustomer> list = salesInvoiceService.getAllTotalRevenueByCustomer(startDate,endDate,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<RevenueCustomer>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/saleInvoices1/revenueByStaff")
    public ResponseEntity<List<RevenueStaff>> getAllRevenueByStaff(
            @RequestParam("startDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<RevenueStaff> list = salesInvoiceService.getAllTotalRevenueByStaff(startDate,endDate,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<RevenueStaff>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/saleInvoices1/searchByStaff")
    public ResponseEntity<List<SalesInvoice>> getSaleInvoicesByStaff(
            @RequestParam("id") @DateTimeFormat(pattern="yyyy-MM-dd") Long id,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<SalesInvoice> list = salesInvoiceService.getAllSalesInvoiceByStaff(id,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<SalesInvoice>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/saleInvoices1/searchByCustomer")
    public ResponseEntity<List<SalesInvoice>> getSaleInvoicesByCustomer(
            @RequestParam("id") @DateTimeFormat(pattern="yyyy-MM-dd") Long id,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<SalesInvoice> list = salesInvoiceService.getAllSalesInvoiceByCustomer(id,pageNo, pageSize, sortBy);

        return new ResponseEntity<List<SalesInvoice>>(list, new HttpHeaders(), HttpStatus.OK);
    }







}
