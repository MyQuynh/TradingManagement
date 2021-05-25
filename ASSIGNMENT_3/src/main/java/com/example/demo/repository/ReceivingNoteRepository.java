package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface ReceivingNoteRepository extends JpaRepository<ReceivingNote, Long> {

    List<ReceivingNote> findAll();

    List<ReceivingNote> findReceivingNotesByDateBetween(String date, String date2);

    ReceivingNote findReceivingNoteById(Long receivingNoteId);

    // Find by staff
    List<ReceivingNote> findReceivingNotesByStaff(Staff staff);

    // Paging
    Page<ReceivingNote> findAll(Pageable pageable);

    Page<ReceivingNote> findReceivingNotesByDateBetween(String date, String date2, Pageable pageable);

    // Find by staff
    Page<ReceivingNote> findReceivingNotesByStaff(Staff staff, Pageable pageable);

}
