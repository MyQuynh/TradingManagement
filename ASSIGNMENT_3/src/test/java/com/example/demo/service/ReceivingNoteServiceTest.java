package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ReceivingNoteRepository;
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
class ReceivingNoteServiceTest {

    @InjectMocks
    ReceivingNoteService receivingNoteService;

    @Mock
    ReceivingNoteRepository receivingNoteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {

        List<ReceivingNote> receivingNotes = IntStream.range(0, 10)
                .mapToObj(i -> new ReceivingNote(i, "Date", new Staff()))
                .collect(Collectors.toList());
        receivingNoteService.saveAll(receivingNotes);
        Mockito.when(receivingNoteRepository.findAll()).thenReturn(receivingNotes);
        List<ReceivingNote> receivingNotesActual = receivingNoteService.findAll();
        assertEquals(receivingNotesActual.size(), receivingNotes.size());

    }

    @Test
    void findReceivingNoteById() {
        ReceivingNote receivingNote = new ReceivingNote();
        ReceivingDetail receivingDetail = new ReceivingDetail();

        List<ReceivingDetail> receivingDetails = new ArrayList<>();
        receivingDetails.add(receivingDetail);

        receivingNote.setReceivingDetailList(receivingDetails);

        receivingNoteService.save(receivingNote);
        Mockito.when(receivingNoteRepository.findReceivingNoteById(1L)).thenReturn(receivingNote);
        ReceivingNote receivingNoteActual = receivingNoteService.findReceivingNoteById(1L);
        assertEquals(receivingNoteActual, receivingNote);
    }

    @Test
    void save() {
        ReceivingNote receivingNoteTest = new ReceivingNote();
        ReceivingDetail receivingDetail = new ReceivingDetail();
        List<ReceivingDetail> receivingDetails = new ArrayList<>();
        receivingDetails.add(receivingDetail);
        receivingNoteTest.setId(11L);
        receivingNoteTest.setReceivingDetailList(receivingDetails);
        // Customer customer1 = customerService.save(customer);
        // assertEquals(customer, customer1);
        Mockito.when(receivingNoteRepository.save(Mockito.any(ReceivingNote.class))).thenReturn(receivingNoteTest);
        Long receivingNoteId = receivingNoteService.save(receivingNoteTest).getId();
        assertEquals(11, receivingNoteId);

    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {

        ReceivingNote receivingNoteTest = new ReceivingNote();
        ReceivingDetail receivingDetail = new ReceivingDetail();
        List<ReceivingDetail> receivingDetails = new ArrayList<>();
        receivingDetails.add(receivingDetail);
        receivingNoteTest.setId(11L);
        receivingNoteTest.setReceivingDetailList(receivingDetails);
        receivingNoteService.save(receivingNoteTest);
        receivingNoteService.deleteById(11L);
        Mockito.verify(receivingNoteRepository, times(1)).deleteById(receivingNoteTest.getId());
        assertNull(receivingNoteService.findReceivingNoteById(11L));

    }

    @Test
    void existsById() {
    }

    @Test
    void updateReceivingNote() {
        String dateBefore = "2020-03-03";
        String dateUpdated = "2020-09-05";

        List<ReceivingDetail> receivingDetails = new ArrayList<>();
        receivingDetails.add(new ReceivingDetail());

        ReceivingNote receivingNoteBefore = new ReceivingNote();

        receivingNoteBefore.setDate(dateBefore);
        receivingNoteBefore.setReceivingDetailList(receivingDetails);

        ReceivingNote receivingNoteUpdated = new ReceivingNote();
        receivingNoteUpdated.setId(0L);
        receivingNoteUpdated.setDate(dateUpdated);
        receivingNoteUpdated.setReceivingDetailList(receivingDetails);

        when(receivingNoteRepository.findReceivingNoteById((receivingNoteBefore.getId()))).thenReturn(receivingNoteBefore);
        when(receivingNoteRepository.save(any(ReceivingNote.class))).thenReturn(receivingNoteUpdated);

        ReceivingNote receivingNoteActual = receivingNoteService.updateReceivingNote(receivingNoteUpdated);

        assertEquals(receivingNoteUpdated.getDate(), receivingNoteActual.getDate());
        assertNotEquals(dateBefore, receivingNoteActual.getDate());
    }

    @Test
    void findDateBetween() {
        Date date = new Date();
        List<ReceivingNote> receivingNoteList = IntStream.range(0, 10)
                .mapToObj(i -> new ReceivingNote(i, "Date", new Staff()))
                .collect(Collectors.toList());
        when(receivingNoteRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(date, date)).thenReturn(receivingNoteList);
        List<ReceivingNote> actualReceivingNoteList = receivingNoteService.findDateBetween(date, date);
        assertEquals(actualReceivingNoteList.size(), receivingNoteList.size());
    }

}