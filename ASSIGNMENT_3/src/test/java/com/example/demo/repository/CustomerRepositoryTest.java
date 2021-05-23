package com.example.demo.repository;

import com.example.demo.config.AppConfig;
import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    TestEntityManager entityManager;

    @Before
    public void before() {
        customerRepository.deleteAll();
        customerRepository.flush();
    }

    @Test
    void findByFirstName() {
        Customer customer = new Customer();
        customer.setFirstName("TestFirstName");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findByFirstName("TestFirstName").contains(customer));

    }

    @Test
    void findByLastName() {
        Customer customer = new Customer();
        customer.setLastName("TestLastName");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findByLastName("TestLastName").contains(customer));
    }

    @Test
    void findCustomersByAddress() {
        Customer customer = new Customer();
        customer.setAddress("TestAddress");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findCustomersByAddress("TestAddress").contains(customer));
    }

    @Test
    void findCustomersByContactPerson() {
        Customer customer = new Customer();
        customer.setContactPerson("Test");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findCustomersByContactPerson("Test").contains(customer));
    }

    @Test
    void findCustomersByPhone() {
        Customer customer = new Customer();
        customer.setPhone("Test");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findCustomersByPhone("Test").contains(customer));
    }

    @Test
    void findCustomersByFax() {
        Customer customer = new Customer();
        customer.setFax("Test");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findCustomersByFax("Test").contains(customer));
    }

    @Test
    void findCustomersByEmail() {
        Customer customer = new Customer();
        customer.setEmail("Test");
        customer = entityManager.persistAndFlush(customer);
        assertTrue( customerRepository.findCustomersByEmail("Test").contains(customer));
    }

    @Test
    void findCustomersById() {
        Customer customer = new Customer();
        customer = entityManager.persistAndFlush(customer);
        assertEquals(customerRepository.findCustomersById(customer.getId()), customer);
    }

    @Test
    void saveAndFindAllCustomer(){
        Customer customer = new Customer();
        customer = entityManager.persistAndFlush(customer);
        assertTrue(customerRepository.findAll().contains(customer));

    }

    @Test
    void delete(){
        Customer customer = new Customer();
        customer = entityManager.persistAndFlush(customer);
        customerRepository.delete(customer);
        assertFalse(customerRepository.findAll().contains(customer));
    }
}