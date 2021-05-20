package com.example.demo.repository;


import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {

    List<DeliveryNote> findAll();
//    List<DeliveryNote> findAllDeliveryNoteBetween(Date deliveryNoteStart, Date deliveryNoteEnd);
    List<DeliveryNote> findAllByDateLessThanEqualAndDateGreaterThanEqual(Date deliveryNoteStart, Date deliveryNoteEnd);
    DeliveryNote findDeliveryNoteById(Long deliveryNoteId);
}
