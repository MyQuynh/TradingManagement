package com.example.demo.repository;

import com.example.demo.model.DeliveryNote;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {

    List<SalesInvoice> findByFirstName(String FirstName);
    List<SalesInvoice> findAll();

    SalesInvoice findSalesInvoiceById(Long salesInvoiceId);

}
