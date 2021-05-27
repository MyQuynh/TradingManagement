package com.example.demo.service;

import com.example.demo.manager.DateManager;
import com.example.demo.model.*;
import com.example.demo.repository.DeliveryNoteRepository;
import com.example.demo.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    DateManager dateManager = new DateManager();

    @Test
    void findAll() {
        List<Order> orders = IntStream.range(0, 10)
                .mapToObj(i -> new Order(i,"Date", new Staff(), new Provider()))
                .collect(Collectors.toList());
        orderService.saveAll(orders);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        List<Order> orderActual = orderService.findAll();
        assertEquals(orderActual.size(), orders.size());
    }

    @Test
    void findOrderById() {
        Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);

        order.setOrderDetails(orderDetails);

        orderService.save(order);
        Mockito.when(orderRepository.findOrderById(1L)).thenReturn(order);
        Order orderActual = orderService.findOrderById(1L);
        assertEquals(orderActual, order);
    }

    @Test
    void save() {
        Order order = new Order();
        OrderDetail orderDetail = new OrderDetail();
        order.setId(11L);
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        order.setId(11L);
        order.setOrderDetails(orderDetails);
        // Customer customer1 = customerService.save(customer);
        // assertEquals(customer, customer1);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        Long orderId = orderService.save(order).getId();
        assertEquals(11, orderId);
    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {
        Order orderTest = new Order();
        OrderDetail orderDetail = new OrderDetail();
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(orderDetail);
        orderTest.setId(11L);
        orderTest.setOrderDetails(orderDetails);
        orderService.save(orderTest);
        orderService.deleteById(11L);
        Mockito.verify(orderRepository, times(1)).deleteById(orderTest.getId());
        assertNull(orderService.findOrderById(11L));
    }

    @Test
    void existsById() {
    }

    @Test
    void updateOrder() {

        String dateBefore = "2020-03-03";
        String dateUpdated = "2020-09-05";

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());

        Order orderBefore = new Order();

        orderBefore.setDate(dateBefore);
        orderBefore.setOrderDetails(orderDetails);

        Order orderUpdated = new Order();
        orderUpdated.setId(0L);
        orderUpdated.setDate(dateUpdated);
        orderUpdated.setOrderDetails(orderDetails);

        when(orderRepository.findOrderById(orderBefore.getId())).thenReturn(orderBefore);
        when(orderRepository.save(any(Order.class))).thenReturn(orderUpdated);

        Order orderActual = orderService.updateOrder(orderUpdated);

        assertEquals(orderUpdated.getDate(), orderActual.getDate());
        assertNotEquals(dateBefore, orderActual.getDate());

    }

    @Test
    void findDateBetween() {
        String startDate = "2023-01-01";
        String endDate  = "2024-01-01";
        List<Order> orderList = IntStream.range(0, 10)
                .mapToObj(i -> new Order(i, "Date",new Staff(), new Provider()))
                .collect(Collectors.toList());
        when(orderRepository.findOrdersByDateBetween(startDate,endDate)).thenReturn(orderList);
        List<Order> actualOrderList = orderService.findDateBetween(dateManager.convertStringToDate(startDate), dateManager.convertStringToDate(endDate));
        assertEquals(actualOrderList.size(), orderList.size());

    }
}