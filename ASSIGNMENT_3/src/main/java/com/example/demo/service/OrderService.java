package com.example.demo.service;

import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {

        var it = orderRepository.findAll();

        var orders = new ArrayList<Order>();
        it.forEach(e -> orders.add(e));

        return orders;
    }

    public Order findOrderById(Long orderId){
        return orderRepository.findOrderById(orderId);
    }


    public Order save(Order order){

        orderRepository.save(order);
        return order;
    }

    public void saveAll(List<Order> orders){
        orderRepository.saveAll(orders);
    }

    public void deleteById(Long orderId){
        orderRepository.deleteById(orderId);
    }

    public boolean existsById(Long orderId){
        return orderRepository.existsById(orderId);
    }

    public Order updateOrder(Order order){
        Order updateOrder = orderRepository.findOrderById((order.getId()));
        updateOrder.setDate(order.getDate());
        updateOrder.setStaff_id(order.getStaff_id());
        return orderRepository.save(updateOrder);
    }

    // Filter by date between start date and end date
    public List<Order> findDateBetween(Date startDate, Date endDate){
        return orderRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(startDate, endDate);
    }


}
