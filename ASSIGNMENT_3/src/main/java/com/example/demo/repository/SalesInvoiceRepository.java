package com.example.demo.repository;

import com.example.demo.model.DeliveryNote;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {

    @EntityGraph(attributePaths = {"saleDetailList"})
    List<SalesInvoice> findAll();

//    List<SalesInvoice> findAllSalesInvoiceBetween(Date orderStart, Date orderEnd);

    SalesInvoice findSalesInvoiceById(Long salesInvoiceId);

    @Query(value = "SELECT customer_id, SUM(totalValue) from sales_invoice  \n" +
            "WHERE date BETWEEN :startDate AND :endDate\n" +
            "GROUP BY customer_id",
            nativeQuery = true)
    List<SalesInvoice> totalRevenueByCustomer(@Param("startDate") Date startDate,
                         @Param("endDate") Date endDate);

    @Query(value = "SELECT staff_id, SUM(totalValue) from sales_invoice  \n" +
            "WHERE date BETWEEN :startDate AND :endDate\n" +
            "GROUP BY staff_id",
            nativeQuery = true)
    List<Object[]> totalRevenueByStaff(@Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate);

    List<SalesInvoice> findAllByDateLessThanEqualAndDateGreaterThanEqual(Date saleInvoiceStart, Date saleInvoiceEnd);


}
