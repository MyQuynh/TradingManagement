package com.example.demo.repository;

import com.example.demo.model.ReceivingDetail;
import com.example.demo.model.ReceivingNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivingDetailRepository extends JpaRepository<ReceivingDetail, Long> {
}
