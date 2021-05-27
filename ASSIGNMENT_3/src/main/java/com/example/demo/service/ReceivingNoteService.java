package com.example.demo.service;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.example.demo.repository.ReceivingDetailRepository;
import com.example.demo.repository.ReceivingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReceivingNoteService {

    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;
    private ReceivingDetailRepository receivingDetailRepository;

    DateManager dateManager = new DateManager();

    private StaffService staffRepository;

    public List<ReceivingNote> findAll() {

        return receivingNoteRepository.findAll();

    }

    public ReceivingNote findReceivingNoteById(Long receivingNoteId){
        return receivingNoteRepository.findReceivingNoteById(receivingNoteId);
    }


    public ReceivingNote save(ReceivingNote receivingNote){
        for(ReceivingDetail receivingDetail: receivingNote.getReceivingDetailList()){
            receivingDetail.setReceivingNote(receivingNote);
        }
        receivingNoteRepository.save(receivingNote);
        return receivingNote;
    }

    public void saveAll(List<ReceivingNote> receivingNotes){
        receivingNoteRepository.saveAll(receivingNotes);
    }

    public String deleteById(Long receivingNoteId){
        receivingNoteRepository.deleteById(receivingNoteId);
        return "SUCCESS";
    }

    public boolean existsById(Long receivingNoteId){
        return receivingNoteRepository.existsById(receivingNoteId);
    }

    public ReceivingNote updateReceivingNote(ReceivingNote receivingNote){
        ReceivingNote updateReceivingNote = receivingNoteRepository.findReceivingNoteById((receivingNote.getId()));
        updateReceivingNote.setDate(receivingNote.getDate());
        updateReceivingNote.setStaff(receivingNote.getStaff());
        return receivingNoteRepository.save(updateReceivingNote);
    }

    // Filter by date between start date and end date
    public List<ReceivingNote> findDateBetween(Date startDate, Date endDate){
        return receivingNoteRepository.findReceivingNotesByDateBetween(dateManager.convertDateToString(startDate), dateManager.convertDateToString(endDate));
    }


    // Add order detail to order (is there need to add the order note into system)
    public void addReceivingDetailToReceivingNote(Long receivingNoteId, ReceivingDetail receivingDetail) throws ResourcesNotFoundException {
        //LOG.info("CourseId :: {} , Student :: {}", courseId, students);
        Optional<ReceivingNote> receivingNoteOptional = receivingNoteRepository.findById(receivingNoteId);
        if (receivingNoteOptional.isEmpty()) {
            throw new ResourcesNotFoundException("Failed to add ReceivingDetail. Invalid ReceivingNoteId :: " + receivingNoteId);
        }
        ReceivingNote receivingNote = receivingNoteOptional.get();
        List<ReceivingDetail> receivingDetails = receivingNote.getReceivingDetailList();
        receivingDetails.add(receivingDetail);
        receivingNote.setReceivingDetailList(receivingDetails);

        // Save also in the order detail and order database
        receivingDetailRepository.save(receivingDetail);
        receivingNoteRepository.save(receivingNote);

    }

    // Filter by staff
    public List<ReceivingNote> findByStaff(Long staff_id){
        Staff staff = staffRepository.findStaffById(staff_id);
        return receivingNoteRepository.findReceivingNotesByStaff(staff);
    }

    // Paging
    public List<ReceivingNote> getAllReceivingNote(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<ReceivingNote> pagedResult = receivingNoteRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ReceivingNote>();
        }
    }

    public List<ReceivingNote> getAllReceivingNoteBetween(Date startDate, Date endDate,Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<ReceivingNote> pagedResult = receivingNoteRepository.findReceivingNotesByDateBetween(dateManager.convertDateToString(startDate),dateManager.convertDateToString(endDate),paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ReceivingNote>();
        }
    }

}
