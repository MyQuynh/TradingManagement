package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepositoryMock;

    @InjectMocks
    private CustomerService customerService;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void findAll() {
    }

    @Test
    void findCustomersById() {
    }

    @Test
    void save() {
        Customer customer = new Customer();
        customer.setId(11L);
        Mockito.when(customerRepositoryMock.save(Mockito.any(Customer.class))).thenReturn(customer);
        Long customerId = customerService.save(customer).getId();
        assertEquals(11, customerId);
    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {
        Customer customerTest = new Customer();
        customerTest.setId(11L);
        customerService.save(customerTest);
//        Customer customer = customerRepositoryMock.findCustomersById(11L);
        Mockito.when(customerRepositoryMock.findById(11L)).thenReturn(Optional.of(customerTest));
        customerService.deleteById(11L);
        Mockito.verify(customerRepositoryMock, times(1)).deleteById(customerTest.getId());
        assertNull(customerService.findCustomersById(11L));
    }

    @Test
    void existsById() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void findByFirstName() {

    }

    @Test
    void findByLastName() {
    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByPhone() {
    }

    @Test
    void findByFax() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByContactPerson() {
    }
}