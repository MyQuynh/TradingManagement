package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ReceivingNoteRepository;
import com.example.demo.repository.SalesInvoiceRepository;
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

        salesInvoice.setSaleDetailList(saleDetails);

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
        salesInvoiceTest.setSaleDetailList(saleDetails);
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
        salesInvoiceTest.setSaleDetailList(saleDetails);
        salesInvoiceService.save(salesInvoiceTest);
        salesInvoiceService.deleteById(11L);
        Mockito.verify(salesInvoiceRepository, times(1)).deleteById(salesInvoiceTest.getId());
        assertNull(salesInvoiceService.findSalesInvoiceById(11L));

    }

    @Test
    void existsById() {
    }

    @Test
    void updateSalesInvoice() {
        String dateBefore = "2020-03-03";
        String dateUpdated = "2020-09-05";

        List<SaleDetail> saleDetails = new ArrayList<>();
        saleDetails.add(new SaleDetail());

        SalesInvoice salesInvoiceBefore = new SalesInvoice();

        salesInvoiceBefore.setDate(dateBefore);
        salesInvoiceBefore.setSaleDetailList(saleDetails);

        SalesInvoice salesInvoiceUpdated = new SalesInvoice();
        salesInvoiceUpdated.setId(0L);
        salesInvoiceUpdated.setDate(dateUpdated);
        salesInvoiceUpdated.setSaleDetailList(saleDetails);

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
        when(salesInvoiceRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(date, date)).thenReturn(salesInvoices);
        List<SalesInvoice> actualSaleInvoiceList = salesInvoiceService.findDateBetween(date, date);
        assertEquals(actualSaleInvoiceList.size(), salesInvoices.size());
    }
}