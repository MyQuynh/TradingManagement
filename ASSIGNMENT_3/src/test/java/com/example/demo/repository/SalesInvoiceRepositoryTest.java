package com.example.demo.repository;

import com.example.demo.model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class SalesInvoiceRepositoryTest {

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findAll() {
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        System.out.println(salesInvoiceRepository.findAll());
    }

    @Test
    void findSalesInvoiceById() {
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        assertEquals(salesInvoiceRepository.findSalesInvoiceById(salesInvoice.getId()), salesInvoice);

    }

    @Test
    void findSalesInvoicesByCustomer() {
        SalesInvoice salesInvoice = new SalesInvoice();
        Customer customer = new Customer();
        Customer customerWrong = new Customer();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        customer = entityManager.persistAndFlush(customer);
        customerWrong = entityManager.persistAndFlush(customerWrong);
        salesInvoice.setCustomer(customer);

        assertTrue(salesInvoiceRepository.findSalesInvoicesByCustomer(customer).contains(salesInvoice));
        assertFalse(salesInvoiceRepository.findSalesInvoicesByCustomer(customerWrong).contains(salesInvoice));
    }

    @Test
    void findSalesInvoicesByStaff() {
        SalesInvoice salesInvoice = new SalesInvoice();
        Staff staff = new Staff();
        Staff staffWrong = new Staff();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        staff = entityManager.persistAndFlush(staff);
        staffWrong = entityManager.persistAndFlush(staffWrong);
        salesInvoice.setStaff(staff);

        assertTrue(salesInvoiceRepository.findSalesInvoicesByStaff(staff).contains(salesInvoice));
        assertFalse(salesInvoiceRepository.findSalesInvoicesByStaff(staffWrong).contains(salesInvoice));
    }

    @Test
    void totalRevenueByCustomer() {
    }

    @Test
    void totalRevenueByStaff() {
    }

    @Test
    void findAllByDateLessThanEqualAndDateGreaterThanEqual() {
        SalesInvoice salesInvoiceRight = new SalesInvoice();
        salesInvoiceRight.setDate("2020-01-01");

        SalesInvoice salesInvoiceWrong = new SalesInvoice();
        salesInvoiceWrong.setDate("2023-01-01");

        String startDate = "2020-01-01";
        String endDate = "2021-01-01";


        salesInvoiceRight = entityManager.persistAndFlush(salesInvoiceRight);
        salesInvoiceWrong = entityManager.persistAndFlush(salesInvoiceWrong);


        assertTrue(salesInvoiceRepository.findSalesInvoicesByDateBetween(startDate, endDate).contains(salesInvoiceRight));
        assertFalse(salesInvoiceRepository.findSalesInvoicesByDateBetween(startDate, endDate).contains(salesInvoiceWrong));
    }

    @Test
    // TODO: CHANGING THE DATETIME
    void delete(){
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        salesInvoiceRepository.delete(salesInvoice);
        assertFalse(salesInvoiceRepository.findAll().contains(salesInvoice));
    }
}