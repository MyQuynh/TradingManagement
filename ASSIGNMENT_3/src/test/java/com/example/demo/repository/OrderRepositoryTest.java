package com.example.demo.repository;

import com.example.demo.manager.DateManager;
import com.example.demo.model.DeliveryNote;
import com.example.demo.model.Order;
import com.example.demo.model.Provider;
import com.example.demo.model.Staff;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    TestEntityManager entityManager;

    DateManager dateManager = new DateManager();

    @Test
    void saveAndFindAll() {
        Order order = new Order();
        order = entityManager.persistAndFlush(order);
        System.out.println(orderRepository.findAll());
        //assertTrue(orderRepository.findAll().contains(order));
    }

    @Test
    void findOrdersByDateBetween() {
        Order orderRight = new Order();
        orderRight.setDate("2020-01-01");

        Order orderWrong = new Order();
        orderWrong.setDate("2023-01-01");

        String startDate = "2020-01-01";
        String endDate = "2021-01-01";


        orderRight = entityManager.persistAndFlush(orderRight);
        orderWrong = entityManager.persistAndFlush(orderWrong);


        assertTrue(orderRepository.findOrdersByDateBetween(startDate, endDate).contains(orderRight));
        assertFalse(orderRepository.findOrdersByDateBetween(startDate, endDate).contains(orderWrong));

    }

    @Test
    void findOrderById() {
        Order order = new Order();
        order = entityManager.persistAndFlush(order);
        assertEquals(orderRepository.findOrderById(order.getId()), order);
    }

    @Test
    void findOrdersByStaff() {

        Order order = new Order();
        Staff staff = new Staff();
        Staff staffWrong = new Staff();
        order = entityManager.persistAndFlush(order);
        staff = entityManager.persistAndFlush(staff);
        staffWrong = entityManager.persistAndFlush(staffWrong);
        order.setStaff(staff);

        assertTrue(orderRepository.findOrdersByStaff(staff).contains(order));
        assertFalse(orderRepository.findOrdersByStaff(staffWrong).contains(order));

    }

    @Test
    void findOrdersByProvider() {
        Order order = new Order();
        Provider provider = new Provider();
        Provider providerWrong = new Provider();
        order = entityManager.persistAndFlush(order);
        provider = entityManager.persistAndFlush(provider);
        providerWrong = entityManager.persistAndFlush(providerWrong);
        order.setProvider(provider);

        assertTrue(orderRepository.findOrdersByProvider(provider).contains(order));
        assertFalse(orderRepository.findOrdersByProvider(providerWrong).contains(order));
    }

    @Test
    void delete(){
        Order order = new Order();
        order = entityManager.persistAndFlush(order);
        orderRepository.delete(order);
        assertFalse(orderRepository.findAll().contains(order));
    }
}