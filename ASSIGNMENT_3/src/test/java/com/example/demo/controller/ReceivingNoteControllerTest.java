package com.example.demo.controller;

import com.example.demo.manager.DateManager;
import com.example.demo.model.Order;
import com.example.demo.model.Provider;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.Staff;
import com.example.demo.service.OrderService;
import com.example.demo.service.ReceivingNoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class ReceivingNoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReceivingNoteService receivingNoteService;

    ObjectMapper mapper  = new ObjectMapper();

    DateManager dateManager = new DateManager();

    @Test
    void findAll() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();

        List<ReceivingNote> receivingNotes = IntStream.range(0, 10)
                .mapToObj(i -> new ReceivingNote(i,sDate1,staff))
                .collect(Collectors.toList());

        given(receivingNoteService.findAll()).willReturn(receivingNotes);

        mvc.perform(get("/api/v1/receivingNotes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].date").value(sDate1))
                .andExpect(jsonPath("$[0].staff.id").value(staff.getId()));
    }

    @Test
    void findReceivingNoteById() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();

        ReceivingNote receivingNote = new ReceivingNote(0L,sDate1,staff);

        given(receivingNoteService.findReceivingNoteById(receivingNote.getId())).willReturn(receivingNote);

        mvc.perform(get("/api/v1/receivingNotes/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()));
    }

    @Test
    void createReceivingNote() throws Exception {
        String sDate1="2001-07-04";
        Staff staff = new Staff();

        ReceivingNote receivingNote = new ReceivingNote(0L,sDate1,staff);
        given(receivingNoteService.save(any(ReceivingNote.class))).willReturn(receivingNote);
        mvc.perform(post("/api/v1/receivingNotes/add")
                .content(mapper.writeValueAsString(receivingNote)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(sDate1))
                .andExpect(jsonPath("$.staff.id").value(staff.getId()));
    }

    @Test
    void updateReceivingNote() {
    }

    @Test
    void deleteReceivingNote() throws Exception {
        Mockito.when(receivingNoteService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/receivingNotes/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDataByDate() throws Exception {
        Date startDate = dateManager.convertStringToDate("2020-02-01");
        Date endDate = dateManager.convertStringToDate("2021-02-01");


        List<ReceivingNote> receivingNotes = IntStream.range(0, 10)
                .mapToObj(i -> new ReceivingNote(i, dateManager.convertDateToString(dateManager.between(startDate, endDate)), new Staff()))
                .collect(Collectors.toList());


        given(receivingNoteService.findDateBetween(startDate,endDate)).willReturn(receivingNotes);

        mvc.perform(get("/api/v1/receivingNotes/searchByDate/").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", dateManager.convertDateToString(startDate))
                .param("endDate", dateManager.convertDateToString(endDate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(receivingNotes.size())));
    }
}