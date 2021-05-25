package com.example.demo.repository;

import com.example.demo.manager.DateManager;
import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Staff;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class DeliveryNoteRepositoryTest {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    @Autowired
    TestEntityManager entityManager;

    DateManager dateManager = new DateManager();

    @Test
    void saveAndFindAll() {
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote = entityManager.persistAndFlush(deliveryNote);
        assertTrue(deliveryNoteRepository.findAll().contains(deliveryNote));
    }

    @Test
    void findAllByDateLessThanEqualAndDateGreaterThanEqual() {
        DeliveryNote deliveryNoteRight = new DeliveryNote();
        deliveryNoteRight.setDate(dateManager.convertStringToDate("2020-02-01"));

        DeliveryNote deliveryNoteWrong = new DeliveryNote();
        deliveryNoteWrong.setDate(dateManager.convertStringToDate("2023-01-01"));

        Date startDate = dateManager.convertStringToDate("2020-01-01");
        Date endDate = dateManager.convertStringToDate("2021-01-01");


        System.out.println(startDate);
        System.out.println(endDate);

        deliveryNoteRight = entityManager.persistAndFlush(deliveryNoteRight);
        deliveryNoteWrong = entityManager.persistAndFlush(deliveryNoteWrong);

        assertTrue(deliveryNoteRepository.findDeliveryNotesByDateBetween(startDate, endDate).contains(deliveryNoteRight));
        assertFalse(deliveryNoteRepository.findDeliveryNotesByDateBetween(startDate, endDate).contains(deliveryNoteWrong));

    }

    @Test
    void findDeliveryNoteById() {
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote = entityManager.persistAndFlush(deliveryNote);
        assertEquals(deliveryNoteRepository.findDeliveryNoteById(deliveryNote.getId()), deliveryNote);
    }

    @Test
    void findDeliveryNotesByStaff() {
        DeliveryNote deliveryNote = new DeliveryNote();
        Staff staff = new Staff();
        staff.setFirstName("Test");
        staff = entityManager.persistAndFlush(staff);
        deliveryNote.setStaff(staff);
        deliveryNote = entityManager.persistAndFlush(deliveryNote);
        assertTrue(deliveryNoteRepository.findDeliveryNotesByStaff(staff).contains(deliveryNote));
    }

    @Test
    void delete(){
        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote = entityManager.persistAndFlush(deliveryNote);
        deliveryNoteRepository.delete(deliveryNote);
        assertFalse(deliveryNoteRepository.findAll().contains(deliveryNote));
    }
}