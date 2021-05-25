package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

class ReceivingNoteTest {

    @Test
    void getAndSetId() {
        Long receivingNoteId = 0L;
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote.setId(receivingNoteId);
        assertEquals(receivingNoteId, receivingNote.getId());
    }


    @Test
    void getAndSetDate() {
        String date = "2020-02-02";
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote.setDate(date);
        assertEquals(date, receivingNote.getDate());
    }


    @Test
    void getAndSetStaff() {
        Staff staff = new Staff();
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote.setStaff(staff);
        assertEquals(staff, receivingNote.getStaff());
    }


    @Test
    void getAndSetReceivingDetailList() {
        List<ReceivingDetail> receivingDetailList = new ArrayList<>();
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote.setReceivingDetailList(receivingDetailList);
        assertEquals(receivingDetailList.size(), receivingNote.getReceivingDetailList().size());
    }

}