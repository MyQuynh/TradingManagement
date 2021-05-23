package com.example.demo.service;

import com.example.demo.manager.DateManager;
import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryDetail;
import com.example.demo.model.DeliveryNote;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DeliveryNoteRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryNoteServiceTest {


    @InjectMocks
    DeliveryNoteService deliveryNoteService;

    @Mock
    DeliveryNoteRepository deliveryNoteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    DateManager dateManager = new DateManager();



    @Test
    void findAllDeliveryNotes() {
        List<DeliveryNote> deliveryNotesList = IntStream.range(0, 10)
                .mapToObj(i -> new DeliveryNote(i,new Date()))
                .collect(Collectors.toList());
        deliveryNoteService.saveAll(deliveryNotesList);
        Mockito.when(deliveryNoteRepository.findAll()).thenReturn(deliveryNotesList);
        List<DeliveryNote> deliveryActual = deliveryNoteService.findAllDeliveryNotes();
        assertEquals(deliveryActual.size(), deliveryNotesList.size());
    }

    @Test
    void findDeliveryNoteById() {
        DeliveryNote deliveryNote = new DeliveryNote();
        DeliveryDetail deliveryDetail = new DeliveryDetail();

        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        deliveryDetails.add(deliveryDetail);

        deliveryNote.setDeliveryDetails(deliveryDetails);

        deliveryNoteService.save(deliveryNote);
        Mockito.when(deliveryNoteRepository.findDeliveryNoteById(1L)).thenReturn(deliveryNote);
        DeliveryNote deliveryNoteActual = deliveryNoteService.findDeliveryNoteById(1L);
        assertEquals(deliveryNote, deliveryNoteActual);
    }

    @Test
    void save() {
        DeliveryNote deliveryNote = new DeliveryNote();
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        deliveryDetails.add(deliveryDetail);
        deliveryNote.setId(11L);
        deliveryNote.setDeliveryDetails(deliveryDetails);
        // Customer customer1 = customerService.save(customer);
        // assertEquals(customer, customer1);
        Mockito.when(deliveryNoteRepository.save(Mockito.any(DeliveryNote.class))).thenReturn(deliveryNote);
        Long deliveryNoteId = deliveryNoteService.save(deliveryNote).getId();
        assertEquals(11, deliveryNoteId);
    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {
        DeliveryNote deliveryNoteTest = new DeliveryNote();
        DeliveryDetail deliveryDetail = new DeliveryDetail();
        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        deliveryDetails.add(deliveryDetail);
        deliveryNoteTest.setId(11L);
        deliveryNoteTest.setDeliveryDetails(deliveryDetails);
        deliveryNoteService.save(deliveryNoteTest);
        deliveryNoteService.deleteById(11L);
        Mockito.verify(deliveryNoteRepository, times(1)).deleteById(deliveryNoteTest.getId());
        assertNull(deliveryNoteService.findDeliveryNoteById(11L));
    }

    @Test
    void existsById() {
    }

    @Test
    void updateDeliveryNote() throws ParseException {

        String dateBefore = "2020-03-03";
        String dateUpdated = "2020-09-05";

        List<DeliveryDetail> deliveryDetails = new ArrayList<>();
        deliveryDetails.add(new DeliveryDetail());

        DeliveryNote deliveryNoteBefore = new DeliveryNote();

        deliveryNoteBefore.setDate(dateManager.convertStringToDate(dateBefore));
        deliveryNoteBefore.setDeliveryDetails(deliveryDetails);

        DeliveryNote deliveryNoteUpdated = new DeliveryNote();
        deliveryNoteUpdated.setId(0L);
        deliveryNoteUpdated.setDate(dateManager.convertStringToDate(dateUpdated));
        deliveryNoteUpdated.setDeliveryDetails(deliveryDetails);

        when(deliveryNoteRepository.findDeliveryNoteById(deliveryNoteBefore.getId())).thenReturn(deliveryNoteBefore);
        when(deliveryNoteRepository.save(any(DeliveryNote.class))).thenReturn(deliveryNoteUpdated);

        DeliveryNote deliveryNoteActual = deliveryNoteService.updateDeliveryNote(deliveryNoteUpdated);

        assertEquals(deliveryNoteUpdated.getDate(), deliveryNoteActual.getDate());
        assertNotEquals(dateManager.convertStringToDate(dateBefore), deliveryNoteActual.getDate());
    }

    @Test
    void findDateBetween() {
        Date date = new Date();
        List<DeliveryNote> deliveryNotesList = IntStream.range(0, 10)
                .mapToObj(i -> new DeliveryNote(i,date))
                .collect(Collectors.toList());
        when(deliveryNoteRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(date,date)).thenReturn(deliveryNotesList);
        List<DeliveryNote> actualDeliveryNoteList = deliveryNoteService.findDateBetween(date,date);
        assertEquals(actualDeliveryNoteList.size(), deliveryNotesList.size());
    }
}