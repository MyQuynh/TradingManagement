package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.ReceivingNote;
import com.example.demo.repository.ReceivingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivingNoteService {

    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;

    public List<ReceivingNote> findAll() {

        var it = receivingNoteRepository.findAll();

        var receivingNotes = new ArrayList<ReceivingNote>();
        it.forEach(e -> receivingNotes.add(e));

        return receivingNotes;
    }

    public ReceivingNote findReceivingNoteById(Long receivingNoteId){
        return receivingNoteRepository.findReceivingNoteById(receivingNoteId);
    }


    public ReceivingNote save(ReceivingNote receivingNote){
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
}
