package com.example.demo.service;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProviderRepository;
import com.example.demo.repository.StaffRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private OrderDetailRepository orderDetailRepository;

//    private StaffRepository staffRepository;
//    private ProviderRepository providerRepository;

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
        for(OrderDetail orderDetail: order.getOrderDetails()){
            orderDetail.setOrder(order);
        }
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
        updateOrder.setStaff(order.getStaff());
        return orderRepository.save(updateOrder);
    }

    // Filter by date between start date and end date
    public List<Order> findDateBetween(Date startDate, Date endDate){
        return orderRepository.findAllByDateLessThanEqualAndDateGreaterThanEqual(startDate, endDate);
    }

    // Add order detail to order (is there need to add the order note into system)
    public void addOrderDetailToOrder(Long orderId, OrderDetail orderDetail) throws ResourcesNotFoundException {
        //LOG.info("CourseId :: {} , Student :: {}", courseId, students);
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new ResourcesNotFoundException("Failed to add OrderDetail. Invalid OrderId :: " + orderId);
        }
        Order order = orderOptional.get();
        List<OrderDetail> orderDetails = (List<OrderDetail>) order.getOrderDetails();
        orderDetails.add(orderDetail);
        order.setOrderDetails(orderDetails);

        // Save also in the order detail and order database
        orderDetailRepository.save(orderDetail);
        orderRepository.save(order);

    }

//    // Filter by staff
//    public List<Order> findByStaff(Long staff_id){
//        Staff staff = staffRepository.findStaffById(staff_id);
//        return orderRepository.findOrdersByStaff(staff);
//    }
//
//    // Filter by provider
//    public List<Order> findByProvider(Long provider_id){
//        Provider provider = providerRepository.findProviderById(provider_id);
//        return orderRepository.findOrdersByProvider(provider);
//    }


}
