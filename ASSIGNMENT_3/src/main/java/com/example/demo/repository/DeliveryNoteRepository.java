package com.example.demo.repository;


import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Staff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {

    @EntityGraph(attributePaths = {"deliveryDetails"})
    List<DeliveryNote> findAll();

    List<DeliveryNote> findDeliveryNotesByDateBetween(Date startDate, Date endDate);

    DeliveryNote findDeliveryNoteById(Long deliveryNoteId);

    // Find by staff
    List<DeliveryNote> findDeliveryNotesByStaff(Staff staff);
}
