package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    List<Order> findAllOrderBetween(Date orderStart, Date orderEnd);


    Order findOrderById(Long orderId);
}
