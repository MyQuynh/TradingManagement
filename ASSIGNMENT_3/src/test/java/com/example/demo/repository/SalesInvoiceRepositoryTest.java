package com.example.demo.repository;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.path.to.Revenue;
import com.path.to.RevenueCustomer;
import com.path.to.RevenueStaff;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class SalesInvoiceRepositoryTest {

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @Autowired
    TestEntityManager entityManager;

    DateManager dateManager = new DateManager();

    @Test
    void saveAndFindAll() {
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        System.out.println(salesInvoiceRepository.findAll());
    }

    @Test
    void findSalesInvoiceById() {
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);

        SalesInvoice salesInvoice1 = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice1);

        assertEquals(salesInvoiceRepository.findSalesInvoiceById(salesInvoice.getId()), salesInvoice);
        assertEquals(salesInvoiceRepository.findSalesInvoiceById(salesInvoice.getId()), salesInvoice1);

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
    void delete() {
        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        salesInvoiceRepository.delete(salesInvoice);
        assertFalse(salesInvoiceRepository.findAll().contains(salesInvoice));
    }


    @Test
    void totalRevenueByCustomer() {

        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        String date = "2023-08-03";
        String startDateInvalid = "2099-01-01";
        String endDateInvalid = "2099-01-01";

        SaleDetail saleDetail = new SaleDetail();
        SalesInvoice salesInvoice = new SalesInvoice();
        Customer customer = new Customer();
        Product product = new Product();

        int totalValue = 10;

        saleDetail = entityManager.persistAndFlush(saleDetail);
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        customer = entityManager.persistAndFlush(customer);
        product = entityManager.persistAndFlush(product);

        saleDetail.setProduct(product);
        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);

        salesInvoice.setSaleDetails(saleDetails);
        salesInvoice.setCustomer(customer);
        salesInvoice.setTotal_value(totalValue);
        salesInvoice.setDate(date);

        List<SalesInvoice> salesInvoices = new ArrayList<>();
        salesInvoices.add(salesInvoice);
        customer.setSalesInvoiceList(salesInvoices);

        List<RevenueCustomer> revenueCustomers = salesInvoiceRepository.totalRevenueByCustomer(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        List<RevenueCustomer> revenueCustomerFake = salesInvoiceRepository.totalRevenueByCustomer(dateManager.convertStringToDate(startDateInvalid), dateManager.convertStringToDate(endDateInvalid));

        // Get the customer index in the revenue by customer
        int indexCus = 0;
        for (RevenueCustomer revenue : revenueCustomers) {
            if (revenue.getId() == customer.getId()) {
                indexCus = revenueCustomers.indexOf(revenue);
                break;
            }
        }

        assertEquals(revenueCustomers.get(indexCus).getRevenue(), totalValue);
        // assertNotEquals(revenueCustomerFake.size(), 0);

    }

    @Test
    void totalRevenueByStaff() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";

        String startDateInvalid = "2099-01-01";
        String endDateInvalid = "2099-01-01";

        String date = "2023-08-03";


        SaleDetail saleDetail = new SaleDetail();
        SalesInvoice salesInvoice = new SalesInvoice();
        Staff staff = new Staff();
        Product product = new Product();

        int totalValue = 10;

        saleDetail = entityManager.persistAndFlush(saleDetail);
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        staff = entityManager.persistAndFlush(staff);
        product = entityManager.persistAndFlush(product);


        saleDetail.setProduct(product);

        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);

        salesInvoice.setSaleDetails(saleDetails);
        salesInvoice.setStaff(staff);
        salesInvoice.setTotal_value(totalValue);
        salesInvoice.setDate(date);

        List<SalesInvoice> salesInvoices = new ArrayList<>();
        salesInvoices.add(salesInvoice);
        staff.setSalesInvoices(salesInvoices);

        List<RevenueStaff> revenueStaffs = salesInvoiceRepository.totalRevenueByStaff(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        List<RevenueStaff> revenueStaffFake = salesInvoiceRepository.totalRevenueByStaff(dateManager.convertStringToDate(startDateInvalid), dateManager.convertStringToDate(endDateInvalid));
        // Get the customer index in the revenue by customer
        int indexCus = 0;
        for (RevenueStaff revenue : revenueStaffs) {
            if (revenue.getId() == staff.getId()) {
                indexCus = revenueStaffs.indexOf(revenue);
                break;
            }
        }

        assertEquals(revenueStaffs.get(indexCus).getRevenue(), totalValue);

        // Depend on database
        //assertNotEquals(revenueStaffFake.size(), 0);
    }

    @Test
    void totalRevenue() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        String startDateInvalid = "2099-01-01";
        String endDateInvalid = "2099-01-01";
        float revenueRight = 0;
        float revenueWrong = 0;

        List<SalesInvoice> salesInvoices = salesInvoiceRepository.findSalesInvoicesByDateBetween(startDate, endDate);
        for (SalesInvoice salesInvoice : salesInvoices){
            revenueRight += salesInvoice.getTotal_value();
        }

        List<SalesInvoice> salesInvoicesInvalid = salesInvoiceRepository.findSalesInvoicesByDateBetween(startDateInvalid, endDateInvalid);
        for (SalesInvoice salesInvoice : salesInvoices){
            revenueWrong += salesInvoice.getTotal_value();
        }

        Float totalRevenue = salesInvoiceRepository.totalRevenue(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(revenueRight, totalRevenue);

        // This may not effective testing since there might be the case the totalValue is the same for revenueRight and revenueWrong
        // assertNotEquals(revenueWrong, totalRevenue);

    }
}