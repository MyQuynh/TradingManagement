package com.example.demo.repository;


import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {

    List<DeliveryNote> findByFirstName(String FirstName);
    List<DeliveryNote> findAll();

    DeliveryNote findDeliveryNoteById(Long deliveryNoteId);
}
