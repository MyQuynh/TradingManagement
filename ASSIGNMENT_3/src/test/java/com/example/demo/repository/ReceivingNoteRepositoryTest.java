package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.SalesInvoice;
import com.example.demo.model.Staff;
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
class ReceivingNoteRepositoryTest {

    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findAll() {
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote = entityManager.persistAndFlush(receivingNote);
        System.out.println(receivingNoteRepository.findAll());
    }

    @Test
    void findAllByDateLessThanEqualAndDateGreaterThanEqual() {

    }

    @Test
    void findReceivingNotesByDateBetween() {
        ReceivingNote receivingNoteRight = new ReceivingNote();
        receivingNoteRight.setDate("2020-01-01");

        ReceivingNote receivingNoteWrong = new ReceivingNote();
        receivingNoteWrong.setDate("2023-01-01");

        String startDate = "2020-01-01";
        String endDate = "2021-01-01";


        receivingNoteRight = entityManager.persistAndFlush(receivingNoteRight);
        receivingNoteWrong = entityManager.persistAndFlush(receivingNoteWrong);


        assertTrue(receivingNoteRepository.findReceivingNotesByDateBetween(startDate, endDate).contains(receivingNoteRight));
        assertFalse(receivingNoteRepository.findReceivingNotesByDateBetween(startDate, endDate).contains(receivingNoteWrong));
    }

    @Test
    void findReceivingNoteById() {
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote = entityManager.persistAndFlush(receivingNote);
        assertEquals(receivingNoteRepository.findReceivingNoteById(receivingNote.getId()), receivingNote);
    }

    @Test
    void findReceivingNotesByStaff() {
        ReceivingNote receivingNote = new ReceivingNote();
        Staff staff = new Staff();
        Staff staffWrong = new Staff();
        receivingNote = entityManager.persistAndFlush(receivingNote);
        staff = entityManager.persistAndFlush(staff);
        staffWrong = entityManager.persistAndFlush(staffWrong);
        receivingNote.setStaff(staff);

        assertTrue(receivingNoteRepository.findReceivingNotesByStaff(staff).contains(receivingNote));
        assertFalse(receivingNoteRepository.findReceivingNotesByStaff(staffWrong).contains(receivingNote));
    }

    @Test
    void delete(){
        ReceivingNote receivingNote = new ReceivingNote();
        receivingNote = entityManager.persistAndFlush(receivingNote);
        receivingNoteRepository.delete(receivingNote);
        assertFalse(receivingNoteRepository.findAll().contains(receivingNote));
    }
}