package com.example.demo.repository;

import com.example.demo.model.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //@EntityGraph(attributePaths = {"saleDetailList"})
    List<Order> findAll();

    Order findOrderById(Long orderId);

    // Find by staff
    List<Order> findOrdersByStaff(Staff staff);

    // Find by provider
    List<Order> findOrdersByProvider(Provider provider);

    List<Order> findOrdersByDateBetween(String start, String end);

    // Paging
    Page<Order> findAll(Pageable pageable);

    // Find by staff
    Page<Order> findOrdersByStaff(Staff staff, Pageable pageable);

    // Find by provider
    Page<Order> findOrdersByProvider(Provider provider, Pageable pageable);

    Page<Order> findOrdersByDateBetween(String start, String end, Pageable pageable);

}
