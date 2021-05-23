package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ReceivingNoteRepository extends JpaRepository<ReceivingNote, Long> {

    List<ReceivingNote> findAll();

    List<ReceivingNote> findAllByDateLessThanEqualAndDateGreaterThanEqual(Date date, Date date2);

    List<ReceivingNote> findReceivingNotesByDateBetween(String date, String date2);

    ReceivingNote findReceivingNoteById(Long receivingNoteId);

    // Find by staff
    List<ReceivingNote> findReceivingNotesByStaff(Staff staff);

}
