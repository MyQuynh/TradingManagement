package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.ReceivingDetail;
import com.example.demo.model.ReceivingNote;
import com.example.demo.model.Staff;
import com.example.demo.repository.ReceivingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReceivingNoteService {

    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;

//    private StaffService staffRepository;

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

    public void deleteById(Long receivingNoteId){
        receivingNoteRepository.deleteById(receivingNoteId);
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
        return receivingNoteRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(startDate, endDate);
    }

    // Filter by date between start date and end date
    public List<ReceivingNote> findDate1Between(Date startDate, Date endDate){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(startDate);
        String str_endDate = dateFormat.format(endDate);
        return receivingNoteRepository.findReceivingNotesByDateBetween(strDate, str_endDate);
    }

//    // Filter by staff
//    public List<ReceivingNote> findByStaff(Long staff_id){
//        Staff staff = staffRepository.findStaffById(staff_id);
//        return receivingNoteRepository.findReceivingNotesByStaff(staff);
//    }


}
