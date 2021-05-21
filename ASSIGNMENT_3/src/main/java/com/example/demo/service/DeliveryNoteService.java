package com.example.demo.service;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DeliveryNoteRepository;
import com.example.demo.repository.StaffRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryNoteService {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;
//    private StaffRepository staffRepository;

    public List<DeliveryNote> findAllDeliveryNotes() {

        var it = deliveryNoteRepository.findAll();

        var deliveryNotes = new ArrayList<DeliveryNote>();
        it.forEach(e -> deliveryNotes.add(e));

        return deliveryNotes;
    }

    public DeliveryNote findDeliveryNoteById(Long deliveryNoteId){
        return deliveryNoteRepository.findDeliveryNoteById(deliveryNoteId);
    }

    public DeliveryNote save(DeliveryNote deliveryNote){

        for(DeliveryDetail deliveryDetail: deliveryNote.getDeliveryDetails()){
            deliveryDetail.setDeliveryNote(deliveryNote);
        }

        deliveryNoteRepository.save(deliveryNote);

        return deliveryNote;
    }

    public void saveAll(List<DeliveryNote> deliveryNotes){
        deliveryNoteRepository.saveAll(deliveryNotes);
    }

    public void deleteById(Long deliveryNoteId){
        deliveryNoteRepository.deleteById(deliveryNoteId);
    }

    public boolean existsById(Long customerId){
        return deliveryNoteRepository.existsById(customerId);
    }

    public DeliveryNote updateDeliveryNote(DeliveryNote deliveryNote){
        DeliveryNote updateDeliveryNote = deliveryNoteRepository.findDeliveryNoteById((deliveryNote.getId()));
        updateDeliveryNote.setDate(deliveryNote.getDate());
        updateDeliveryNote.setStaff(deliveryNote.getStaff());
        return deliveryNoteRepository.save(updateDeliveryNote);
    }

    // Filter by date between start date and end date
    public List<DeliveryNote> findDateBetween(Date startDate, Date endDate){
        return deliveryNoteRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(startDate, endDate);
    }

//    // Filter by staff
//    public List<DeliveryNote> findByStaff(Long staff_id){
//        Staff staff = staffRepository.findStaffById(staff_id);
//        return deliveryNoteRepository.findDeliveryNotesByStaff(staff);
//    }

    // Add delivery detail to delivery note (is there need to add the delvery note into system)
    public void addDeliveryDetailToDeliveryNote(Long deliveryNoteId, DeliveryDetail deliveryDetail) throws ResourcesNotFoundException {
        //LOG.info("CourseId :: {} , Student :: {}", courseId, students);
        Optional<DeliveryNote> deliveryNoteOptional = deliveryNoteRepository.findById(deliveryNoteId);
        if (deliveryNoteOptional.isEmpty()) {
            throw new ResourcesNotFoundException("Failed to add DeliveryDetail. Invalid DeliveryNoteId :: " + deliveryNoteId);
        }
        DeliveryNote deliveryNote = deliveryNoteOptional.get();
        List<DeliveryDetail> deliveryDetails = deliveryNote.getDeliveryDetails();
        deliveryDetails.add(deliveryDetail);
        deliveryNote.setDeliveryDetails(deliveryDetails);
        deliveryNoteRepository.save(deliveryNote);

    }



}
