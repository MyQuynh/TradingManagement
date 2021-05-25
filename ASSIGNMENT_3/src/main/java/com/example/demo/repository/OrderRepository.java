package com.example.demo.repository;

import com.example.demo.model.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
}
