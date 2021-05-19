package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.model.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceivingNoteRepository extends JpaRepository<ReceivingNote, Long> {

    List<ReceivingNote> findAll();
    List<ReceivingNote> findAllReceivingNoteBetween(Date orderStart, Date orderEnd);

    ReceivingNote findReceivingNoteById(Long receivingNoteId);
}
