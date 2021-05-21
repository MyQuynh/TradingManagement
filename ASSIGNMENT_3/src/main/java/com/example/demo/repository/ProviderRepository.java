package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Provider;
import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findProviderById(Long providerId);

}
