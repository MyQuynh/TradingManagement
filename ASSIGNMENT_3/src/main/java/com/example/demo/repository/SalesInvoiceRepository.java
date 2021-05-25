package com.example.demo.repository;

import com.example.demo.model.*;
import com.path.to.Revenue;
import com.path.to.RevenueCustomer;
import com.path.to.RevenueStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {

    List<SalesInvoice> findAll();

//    List<SalesInvoice> findAllSalesInvoiceBetween(Date orderStart, Date orderEnd);

    SalesInvoice findSalesInvoiceById(Long salesInvoiceId);

    List<SalesInvoice> findSalesInvoicesByCustomer(Customer customer);

    List<SalesInvoice> findSalesInvoicesByStaff(Staff staff);

    @Query(value = "select customer.id as id , COALESCE(sales_invoice.revenue, 0) revenue\n" +
            "from customer\n" +
            "         left join (\n" +
            "    SELECT sales_invoice.customer_id, SUM(total_value) as revenue\n" +
            "    from sales_invoice\n" +
            "    WHERE date BETWEEN :startDate AND :endDate\n" +
            "    GROUP BY sales_invoice.customer_id\n" +
            ") sales_invoice on customer.id = sales_invoice.customer_id;",
            nativeQuery = true)
    List<RevenueCustomer> totalRevenueByCustomer(@Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate);

    @Query(value = "select staff.id as id , COALESCE(sales_invoice.revenue, 0) revenue\n" +
            "from staff\n" +
            "         left join (\n" +
            "    SELECT sales_invoice.staff_id, SUM(total_value) as revenue\n" +
            "    from sales_invoice\n" +
            "    WHERE date BETWEEN :startDate AND :endDate\n" +
            "    GROUP BY sales_invoice.staff_id\n" +
            ") sales_invoice on staff.id = sales_invoice.staff_id;",
            nativeQuery = true)
    List<RevenueStaff> totalRevenueByStaff(@Param("startDate") Date startDate,
                                           @Param("endDate") Date endDate);

    @Query(value = "select COALESCE(sum(sale_detail.quantity * product.price),0) as total_value\n" +
            "from sales_invoice, product, sale_detail\n" +
            "where sales_invoice.id = sale_detail.id\n" +
            "and sale_detail.product_id = product.id\n" +
            "and sales_invoice.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Float totalRevenue(@Param("startDate") Date startDate,
                         @Param("endDate") Date endDate);


    List<SalesInvoice> findSalesInvoicesByDateBetween(String start, String end);

    // Paging
    Page<SalesInvoice> findAll(Pageable pageable);

    Page<SalesInvoice> findSalesInvoicesByCustomer(Customer customer, Pageable pageable);

    Page<SalesInvoice> findSalesInvoicesByStaff(Staff staff, Pageable pageable);

    @Query(value = "select customer.id as id , COALESCE(sales_invoice.revenue, 0) revenue\n" +
            "from customer\n" +
            "         left join (\n" +
            "    SELECT sales_invoice.customer_id, SUM(total_value) as revenue\n" +
            "    from sales_invoice\n" +
            "    WHERE date BETWEEN :startDate AND :endDate\n" +
            "    GROUP BY sales_invoice.customer_id\n" +
            ") sales_invoice on customer.id = sales_invoice.customer_id;",
            nativeQuery = true)
    Page<RevenueCustomer> totalRevenueByCustomer(@Param("startDate") Date startDate,
                                                 @Param("endDate") Date endDate, Pageable pageable);

    @Query(value = "select staff.id as id , COALESCE(sales_invoice.revenue, 0) revenue\n" +
            "from staff\n" +
            "         left join (\n" +
            "    SELECT sales_invoice.staff_id, SUM(total_value) as revenue\n" +
            "    from sales_invoice\n" +
            "    WHERE date BETWEEN :startDate AND :endDate\n" +
            "    GROUP BY sales_invoice.staff_id\n" +
            ") sales_invoice on staff.id = sales_invoice.staff_id;",
            nativeQuery = true)
    Page<RevenueStaff> totalRevenueByStaff(@Param("startDate") Date startDate,
                                           @Param("endDate") Date endDate, Pageable pageable);

    Page<SalesInvoice> findSalesInvoicesByDateBetween(String start, String end, Pageable pageable);



}
