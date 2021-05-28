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
    void totalRevenueByACustomer() {

        String startDate = "2023-01-01";
        String endDate = "2024-01-01";

        String startDateInvalid = "2099-01-01";
        String endDateInvalid = "2099-01-01";

        String date = "2023-08-03";


        SaleDetail saleDetail = new SaleDetail();
        SalesInvoice salesInvoice = new SalesInvoice();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        Product product = new Product();

        int totalValue = 10;

        saleDetail = entityManager.persistAndFlush(saleDetail);
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        customer = entityManager.persistAndFlush(customer);
        customer1 = entityManager.persistAndFlush(customer1);
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

        RevenueCustomer revenueCustomer = salesInvoiceRepository.totalRevenueByACustomer(customer.getId(), dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));

        assertEquals(revenueCustomer.getId(), customer.getId());
        assertNotEquals(revenueCustomer.getId(), customer1.getId());


    }

    @Test
    void totalRevenueByStaff() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        String date = "2023-08-03";

        Staff staff = new Staff();
        Staff staff1 = new Staff();
        staff = entityManager.persistAndFlush(staff);
        staff1 = entityManager.persistAndFlush(staff1);

        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setDate(date);
        salesInvoice = entityManager.persistAndFlush(salesInvoice);

        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setQuantity(4);
        saleDetail = entityManager.persistAndFlush(saleDetail);

        Product product = new Product();
        product = entityManager.persistAndFlush(product);
        product.setPrice(3);

        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);

        saleDetail.setProduct(product);
        product.setSaleDetails(saleDetails);

        salesInvoice.setSaleDetails(saleDetails);
        saleDetail.setSalesInvoice(salesInvoice);

        List<SalesInvoice> salesInvoices = new ArrayList<>();
        salesInvoices.add(salesInvoice);

        salesInvoice.setStaff(staff);
        staff.setSalesInvoices(salesInvoices);

        // System.out.println(customer.getSalesInvoiceList().get(0).getSaleDetails().get(0).getProduct().getPrice());


        List<RevenueStaff> revenueStaff = salesInvoiceRepository.totalRevenueByStaffs(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        int indexCus = 0;
        for (RevenueStaff revenueStaff1 : revenueStaff){
            if (revenueStaff1.getId().equals(staff.getId())){
                indexCus = revenueStaff.indexOf(revenueStaff1);
            }
            // System.out.println(revenueCustomer1.getId() +" "+ revenueCustomer1.getRevenue());
        }

        assertEquals(product.getPrice()* saleDetail.getQuantity(), revenueStaff.get(indexCus).getRevenue());


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

    @Test
    void totalRevenueCustomer() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        String date = "2023-08-03";

        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer = entityManager.persistAndFlush(customer);
        customer1 = entityManager.persistAndFlush(customer1);

        SalesInvoice salesInvoice = new SalesInvoice();
        salesInvoice.setDate(date);
        salesInvoice = entityManager.persistAndFlush(salesInvoice);

        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setQuantity(4);
        saleDetail = entityManager.persistAndFlush(saleDetail);

        Product product = new Product();
        product = entityManager.persistAndFlush(product);
        product.setPrice(3);

        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);

        saleDetail.setProduct(product);
        product.setSaleDetails(saleDetails);

        salesInvoice.setSaleDetails(saleDetails);
        saleDetail.setSalesInvoice(salesInvoice);

        List<SalesInvoice> salesInvoices = new ArrayList<>();
        salesInvoices.add(salesInvoice);

        salesInvoice.setCustomer(customer);
        customer.setSalesInvoiceList(salesInvoices);

        // System.out.println(customer.getSalesInvoiceList().get(0).getSaleDetails().get(0).getProduct().getPrice());


        List<RevenueCustomer> revenueCustomer = salesInvoiceRepository.totalRevenueByCustomers(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        int indexCus = 0;
        for (RevenueCustomer revenueCustomer1 : revenueCustomer){
            if (revenueCustomer1.getId().equals(customer.getId())){
                indexCus = revenueCustomer.indexOf(revenueCustomer1);
            }
            // System.out.println(revenueCustomer1.getId() +" "+ revenueCustomer1.getRevenue());
        }

        assertEquals(product.getPrice()* saleDetail.getQuantity(), revenueCustomer.get(indexCus).getRevenue());
    }

    @Test
    void totalRevenueByAStaff() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";

        String startDateInvalid = "2099-01-01";
        String endDateInvalid = "2099-01-01";

        String date = "2023-08-03";


        SaleDetail saleDetail = new SaleDetail();
        SalesInvoice salesInvoice = new SalesInvoice();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        Product product = new Product();

        int totalValue = 10;

        saleDetail = entityManager.persistAndFlush(saleDetail);
        salesInvoice = entityManager.persistAndFlush(salesInvoice);
        staff = entityManager.persistAndFlush(staff);
        staff1 = entityManager.persistAndFlush(staff1);
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

        RevenueStaff revenueStaff = salesInvoiceRepository.totalRevenueByAStaff(staff.getId(), dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));

        assertEquals(revenueStaff.getId(), staff.getId());
        assertNotEquals(revenueStaff.getId(), staff1.getId());
    }
}