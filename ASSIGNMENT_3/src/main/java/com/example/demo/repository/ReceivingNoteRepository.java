package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceivingNoteRepository extends JpaRepository<ReceivingNote, Long> {
    List<ReceivingNote> findByFirstName(String FirstName);
    List<ReceivingNote> findAll();

    ReceivingNote findReceivingNoteById(Long receivingNoteId);
}
