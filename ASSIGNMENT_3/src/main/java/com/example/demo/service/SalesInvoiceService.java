package com.example.demo.service;

import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SaleDetail;
import com.example.demo.model.SalesInvoice;
import com.example.demo.repository.SalesInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesInvoiceService {


    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    public List<SalesInvoice> findAll() {

        var it = salesInvoiceRepository.findAll();

        var salesInvoices = new ArrayList<SalesInvoice>();
        it.forEach(e -> salesInvoices.add(e));

        return salesInvoices;
    }

    public SalesInvoice findSalesInvoiceById(Long salesInvoiceId){
        return salesInvoiceRepository.findSalesInvoiceById(salesInvoiceId);
    }


    public SalesInvoice save(SalesInvoice salesInvoice){
        for(SaleDetail saleDetail: salesInvoice.getSaleDetailList()){
            saleDetail.setSalesInvoice(salesInvoice);
        }
        salesInvoiceRepository.save(salesInvoice);
        return salesInvoice;
    }

    public void saveAll(List<SalesInvoice> salesInvoices){
        salesInvoiceRepository.saveAll(salesInvoices);
    }

    public void deleteById(Long salesInvoiceId){
        salesInvoiceRepository.deleteById(salesInvoiceId);
    }

    public boolean existsById(Long salesInvoiceId){
        return salesInvoiceRepository.existsById(salesInvoiceId);
    }

    public SalesInvoice updateSalesInvoice(SalesInvoice salesInvoice){
        SalesInvoice updateSalesInvoice = salesInvoiceRepository.findSalesInvoiceById((salesInvoice.getId()));
        updateSalesInvoice.setDate(salesInvoice.getDate());
        updateSalesInvoice.setCustomer(salesInvoice.getCustomer());
        updateSalesInvoice.setStaff(salesInvoice.getStaff());
        updateSalesInvoice.setSaleDetailList(salesInvoice.getSaleDetailList());
        return salesInvoiceRepository.save(updateSalesInvoice);
    }

    // Filter by date between start date and end date
    public List<SalesInvoice> findDateBetween(Date startDate, Date endDate){
        return salesInvoiceRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(startDate, endDate);
    }


}
