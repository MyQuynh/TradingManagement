package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.repository.DeliveryNoteRepository;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeliveryNoteService {

    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    public List<DeliveryNote> findAll() {

        var it = deliveryNoteRepository.findAll();

        var deliveryNotes = new ArrayList<DeliveryNote>();
        it.forEach(e -> deliveryNotes.add(e));

        return deliveryNotes;
    }

    public DeliveryNote findDeliveryNoteById(Long deliveryNoteId){
        return deliveryNoteRepository.findDeliveryNoteById(deliveryNoteId);
    }

    public DeliveryNote save(DeliveryNote deliveryNote){
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
        updateDeliveryNote.setStaff_id(deliveryNote.getStaff_id());
        return deliveryNoteRepository.save(updateDeliveryNote);
    }

    // Filter by date between start date and end date
    public List<DeliveryNote> findAllDeliveryNoteBetween(Date startDate, Date endDate){
        return deliveryNoteRepository.findAllDeliveryNoteBetween(startDate, endDate);
    }


}
