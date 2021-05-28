package com.example.demo.service;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.example.demo.repository.ReceivingNoteRepository;
import com.example.demo.repository.SalesInvoiceRepository;
import com.path.to.RevenueCustomer;
import com.path.to.RevenueStaff;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesInvoiceServiceTest {

    @InjectMocks
    SalesInvoiceService salesInvoiceService;

    @Mock
    SalesInvoiceRepository salesInvoiceRepository;

    DateManager dateManager = new DateManager();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        List<SalesInvoice> salesInvoices = IntStream.range(0, 10)
                .mapToObj(i -> new SalesInvoice(i, "Date", new Staff(), new Customer(),i))
                .collect(Collectors.toList());
        salesInvoiceService.saveAll(salesInvoices);
        Mockito.when(salesInvoiceRepository.findAll()).thenReturn(salesInvoices);
        List<SalesInvoice> salesInvoicesActual = salesInvoiceService.findAll();
        assertEquals(salesInvoicesActual.size(), salesInvoices.size());
    }

    @Test
    void findSalesInvoiceById() {
        SalesInvoice salesInvoice = new SalesInvoice();
        SaleDetail saleDetail = new SaleDetail();

        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);

        salesInvoice.setSaleDetails(saleDetails);

        salesInvoiceService.save(salesInvoice);
        Mockito.when(salesInvoiceRepository.findSalesInvoiceById(1L)).thenReturn(salesInvoice);
        SalesInvoice salesInvoiceActual = salesInvoiceService.findSalesInvoiceById(1L);
        assertEquals(salesInvoiceActual, salesInvoice);
    }

    @Test
    void save() {

        SalesInvoice salesInvoiceTest = new SalesInvoice();
        SaleDetail saleDetail = new SaleDetail();
        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);
        salesInvoiceTest.setId(11L);
        salesInvoiceTest.setSaleDetails(saleDetails);
        // Customer customer1 = customerService.save(customer);
        // assertEquals(customer, customer1);
        Mockito.when(salesInvoiceRepository.save(Mockito.any(SalesInvoice.class))).thenReturn(salesInvoiceTest);
        Long salesInvoiceId = salesInvoiceService.save(salesInvoiceTest).getId();
        assertEquals(11, salesInvoiceId);

    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {

        SalesInvoice salesInvoiceTest = new SalesInvoice();
        SaleDetail saleDetail = new SaleDetail();
        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(saleDetail);
        salesInvoiceTest.setId(11L);
        salesInvoiceTest.setSaleDetails(saleDetails);
        salesInvoiceService.save(salesInvoiceTest);
        salesInvoiceService.deleteById(11L);
        Mockito.verify(salesInvoiceRepository, times(1)).deleteById(salesInvoiceTest.getId());
        assertNull(salesInvoiceService.findSalesInvoiceById(11L));

    }

    @Test
    void updateSalesInvoice() {
        String dateBefore = "2020-03-03";
        String dateUpdated = "2020-09-05";

        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(new SaleDetail());

        SalesInvoice salesInvoiceBefore = new SalesInvoice();

        salesInvoiceBefore.setDate(dateBefore);
        salesInvoiceBefore.setSaleDetails(saleDetails);

        SalesInvoice salesInvoiceUpdated = new SalesInvoice();
        salesInvoiceUpdated.setId(0L);
        salesInvoiceUpdated.setDate(dateUpdated);
        salesInvoiceUpdated.setSaleDetails(saleDetails);

        when(salesInvoiceRepository.findSalesInvoiceById(((salesInvoiceBefore.getId())))).thenReturn(salesInvoiceBefore);
        when(salesInvoiceRepository.save(any(SalesInvoice.class))).thenReturn(salesInvoiceUpdated);

        SalesInvoice salesInvoiceActual = salesInvoiceService.updateSalesInvoice(salesInvoiceUpdated);

        assertEquals(salesInvoiceUpdated.getDate(), salesInvoiceActual.getDate());
        assertNotEquals(dateBefore, salesInvoiceActual.getDate());
    }

    @Test
    void findDateBetween() {
        Date date = new Date();
        List<SalesInvoice> salesInvoices = IntStream.range(0, 10)
                .mapToObj(i -> new SalesInvoice(i, "Date", new Staff(), new Customer(),i))
                .collect(Collectors.toList());
        when(salesInvoiceRepository.findSalesInvoicesByDateBetween(dateManager.convertDateToString(date), dateManager.convertDateToString(date))).thenReturn(salesInvoices);
        List<SalesInvoice> actualSaleInvoiceList = salesInvoiceService.findDateBetween(date, date);
        assertEquals(actualSaleInvoiceList.size(), salesInvoices.size());
    }

    @Test
    void totalRevenue() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        List<RevenueCustomer> revenueCustomers = new ArrayList<>();

        when(salesInvoiceRepository.totalRevenueByCustomers(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).thenReturn(revenueCustomers);
        List<RevenueCustomer> actualRevenueCustomer = salesInvoiceService.revenueCustomers(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(actualRevenueCustomer.size(), revenueCustomers.size());
    }

    @Test
    void revenueCustomer() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        int revenue = 0;

        when(salesInvoiceRepository.totalRevenue(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).thenReturn((float) revenue);
        Float actualRevenue = salesInvoiceService.totalRevenue(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(actualRevenue, revenue);

    }

    @Test
    void revenueStaff() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        List<RevenueStaff> revenueStaffs = new ArrayList<>();
        when(salesInvoiceRepository.totalRevenueByStaffs(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).thenReturn(revenueStaffs);
        List<RevenueStaff> actualRevenueStaff = salesInvoiceService.revenueStaffs(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(actualRevenueStaff.size(), revenueStaffs.size());
    }

    @Test
    void revenueByACustomer() {

        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        Customer customer = new Customer();
        RevenueCustomer1 revenueCustomer1 = new RevenueCustomer1(customer.getId(), 2001);
        when(salesInvoiceRepository.totalRevenueByACustomer(customer.getId(),dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).thenReturn(revenueCustomer1);
        // RevenueStaff actualRevenueStaff = salesInvoiceService.revenueByAStaff(customer.getId(),dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        RevenueCustomer actualRevenueCustomer = salesInvoiceService.revenueByACustomer(customer.getId(),dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(revenueCustomer1.getId(), actualRevenueCustomer.getId());


    }

    @Test
    void revenueByAStaff() {
        String startDate = "2023-01-01";
        String endDate = "2024-01-01";
        Staff staff = new Staff();
        RevenueStaff1 revenueStaff1 = new RevenueStaff1(staff.getId(), (float) 2001);
        when(salesInvoiceRepository.totalRevenueByAStaff(staff.getId(),dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate))).thenReturn(revenueStaff1);
        RevenueStaff actualRevenueStaff = salesInvoiceService.revenueByAStaff(staff.getId(),dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(actualRevenueStaff.getId(), revenueStaff1.getId());
    }
}