package com.example.demo.service;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.SalesInvoiceRepository;
import com.example.demo.repository.StaffRepository;
import com.path.to.Revenue;
import com.path.to.RevenueCustomer;
import com.path.to.RevenueStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesInvoiceService {


    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    DateManager dateManager = new DateManager();

//    private StaffRepository staffRepository;
//    private CustomerRepository customerRepository;

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
        for(SaleDetail saleDetail: salesInvoice.getSaleDetails()){
            saleDetail.setSalesInvoice(salesInvoice);
        }
        salesInvoiceRepository.save(salesInvoice);
        return salesInvoice;
    }

    public void saveAll(List<SalesInvoice> salesInvoices){
        salesInvoiceRepository.saveAll(salesInvoices);
    }

    public String deleteById(Long salesInvoiceId){
        salesInvoiceRepository.deleteById(salesInvoiceId);
        return "SUCCESS";
    }

    public boolean existsById(Long salesInvoiceId){
        return salesInvoiceRepository.existsById(salesInvoiceId);
    }

    public SalesInvoice updateSalesInvoice(SalesInvoice salesInvoice){
        SalesInvoice updateSalesInvoice = salesInvoiceRepository.findSalesInvoiceById((salesInvoice.getId()));
        updateSalesInvoice.setDate(salesInvoice.getDate());
        updateSalesInvoice.setCustomer(salesInvoice.getCustomer());
        updateSalesInvoice.setStaff(salesInvoice.getStaff());
        updateSalesInvoice.setSaleDetails(salesInvoice.getSaleDetails());
        return salesInvoiceRepository.save(updateSalesInvoice);
    }

    // Filter by date between start date and end date
    public List<SalesInvoice> findDateBetween(Date startDate, Date endDate){
        return salesInvoiceRepository.findSalesInvoicesByDateBetween(dateManager.convertDateToString(startDate), dateManager.convertDateToString(endDate));
    }

    public Float totalRevenue(Date startDate, Date endDate){
        return salesInvoiceRepository.totalRevenue(startDate, endDate);
    }

    public List<RevenueCustomer> revenueCustomer(Date startDate, Date endDate){
        return salesInvoiceRepository.totalRevenueByCustomer(startDate, endDate);
    }

    public List<RevenueStaff> revenueStaff(Date startDate, Date endDate){
        return salesInvoiceRepository.totalRevenueByStaff(startDate, endDate);
    }

//    // Filter by staff
//    public List<SalesInvoice> findByStaff(Long staff_id){
//        Staff staff = staffRepository.findStaffById(staff_id);
//        return salesInvoiceRepository.findSalesInvoicesByStaff(staff);
//    }
//
//    // Filter by customer
//    public List<SalesInvoice> findByCustomer(Long customer_id){
//        Customer customer = customerRepository.findCustomersById(customer_id);
//        return salesInvoiceRepository.findSalesInvoicesByCustomer(customer);
//    }


}
