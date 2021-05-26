package com.example.demo.controller;

import com.example.demo.manager.DateManager;
import com.example.demo.model.DeliveryNote;
import com.example.demo.service.DeliveryNoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
//@Sql(scripts = "dummy_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DeliveryNoteControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeliveryNoteService deliveryNoteService;

    ObjectMapper mapper  = new ObjectMapper();

    DateManager dateManager = new DateManager();


    @Test
    void findAllDeliveryNotes() throws Exception {

        String sDate1="2001-07-04T19:08:56.235+00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date1 = dateFormat.parse(sDate1);

        List<DeliveryNote> deliveryNotes = IntStream.range(0, 10)
                .mapToObj(i -> new DeliveryNote(i, date1))
                .collect(Collectors.toList());

        given(deliveryNoteService.findAllDeliveryNotes()).willReturn(deliveryNotes);

        mvc.perform(get("/api/v1/deliveryNotes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].date").value(sDate1));
    }


    @Test
    void findDeliveryNoteById() throws Exception {

        String sDate1="2001-07-04T19:08:56.235+00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date1 = dateFormat.parse(sDate1);

        int deliveryNoteId = 0;
        DeliveryNote deliveryNote = new DeliveryNote(deliveryNoteId, date1);
        given(deliveryNoteService.findDeliveryNoteById((long) deliveryNoteId)).willReturn(deliveryNote);
        mvc.perform(get("/api/v1/deliveryNotes/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.date").value(sDate1));
    }

    @Test
    void createDeliveryNote() throws Exception {
        String sDate1="2001-07-04T19:08:56.235+00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date1 = dateFormat.parse(sDate1);

        int deliveryNoteId = 0;
        DeliveryNote deliveryNote = new DeliveryNote(deliveryNoteId, date1);

        given(deliveryNoteService.save(any(DeliveryNote.class))).willReturn(deliveryNote);
        mvc.perform(post("/api/v1/deliveryNotes/add")
                .content(mapper.writeValueAsString(deliveryNote)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(sDate1)))
        ;


    }

    @Test
    void updateDeliveryNote() throws Exception {

        String sDate1="2001-07-04T19:08:56.235+00:00";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date1 = dateFormat.parse(sDate1);

        int deliveryNoteId = 0;
        DeliveryNote deliveryNote = new DeliveryNote(deliveryNoteId, date1);

        given(deliveryNoteService.updateDeliveryNote(any(DeliveryNote.class))).willReturn(deliveryNote);
        mvc.perform(put("/api/v1/deliveryNotes/{id}", deliveryNote.getId())
                .content(mapper.writeValueAsString(deliveryNote)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(sDate1)))
        ;

    }

    @Test
    void deleteDeliveryNote() throws Exception {
        Mockito.when(deliveryNoteService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/deliveryNotes/{id}", 0L))
                .andExpect(status().isOk());
//        mvc.perform(delete("/api/v1/deliveryNotes/{id}", 0L))
//                .andExpect(status().isNotFound());
    }

    @Test
    //@DatabaseSetup("/delivery.xml")
    void fetchDataByDate() throws Exception {

        String startDate="2020-02-01";
        String endDate="2021-02-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        List<DeliveryNote> deliveryNotes = IntStream.range(0, 10)
                .mapToObj(i -> new DeliveryNote(i, dateManager.between(start, end)))
                .collect(Collectors.toList());


        given(deliveryNoteService.findDateBetween(start,end)).willReturn(deliveryNotes);

        mvc.perform(get("/api/v1/deliveryNotes/searchByDate").contentType(MediaType.APPLICATION_JSON)
                .param("startDate", startDate)
                .param("endDate", endDate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(deliveryNotes.size())));

    }
}