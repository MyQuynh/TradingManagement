package com.example.demo.repository;

import com.example.demo.config.AppConfig;
import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import org.checkerframework.checker.units.qual.C;
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

        Customer customer1  = new Customer();
        customer1.setFirstName("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue(customerRepository.findByFirstName("TestFirstName").contains(customer));
        assertFalse(customerRepository.findByFirstName("TestFirstName").contains(customer1));

    }

    @Test
    void findByLastName() {
        Customer customer = new Customer();
        customer.setLastName("TestLastName");
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1.setLastName("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue(customerRepository.findByLastName("TestLastName").contains(customer));
        assertFalse(customerRepository.findByLastName("TestLastName").contains(customer1));
    }

    @Test
    void findCustomersByAddress() {
        Customer customer = new Customer();
        customer.setAddress("TestAddress");
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1.setAddress("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue( customerRepository.findCustomersByAddress("TestAddress").contains(customer));
        assertFalse( customerRepository.findCustomersByAddress("TestAddress").contains(customer1));
    }

    @Test
    void findCustomersByContactPerson() {
        Customer customer = new Customer();
        customer.setContactPerson("Test");
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1.setContactPerson("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue(customerRepository.findCustomersByContactPerson("Test").contains(customer));
        assertFalse(customerRepository.findCustomersByContactPerson("Test").contains(customer1));

    }

    @Test
    void findCustomersByPhone() {
        Customer customer = new Customer();
        customer.setPhone("Test");
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1.setPhone("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue( customerRepository.findCustomersByPhone("Test").contains(customer));
        assertFalse( customerRepository.findCustomersByPhone("Test").contains(customer1));
    }

    @Test
    void findCustomersByFax() {
        Customer customer = new Customer();
        customer.setFax("Test");
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1.setFax("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue(customerRepository.findCustomersByFax("Test").contains(customer));
        assertFalse(customerRepository.findCustomersByFax("Test").contains(customer1));

    }

    @Test
    void findCustomersByEmail() {
        Customer customer = new Customer();
        customer.setEmail("Test");
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1.setEmail("ThisIsWrong");
        customer1 = entityManager.persistAndFlush(customer1);

        assertTrue( customerRepository.findCustomersByEmail("Test").contains(customer));
        assertFalse( customerRepository.findCustomersByEmail("Test").contains(customer1));

    }

    @Test
    void findCustomersById() {
        Customer customer = new Customer();
        customer = entityManager.persistAndFlush(customer);

        Customer customer1  = new Customer();
        customer1 = entityManager.persistAndFlush(customer1);

        assertEquals(customerRepository.findCustomersById(customer.getId()), customer);
        assertNotEquals(customerRepository.findCustomersById(customer.getId()), customer1);
    }

    @Test
    void saveAndFindAllCustomer(){
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer = entityManager.persistAndFlush(customer);
        assertTrue(customerRepository.findAll().contains(customer));
        assertFalse(customerRepository.findAll().contains(customer1));

    }

    @Test
    void delete(){
        Customer customer = new Customer();
        customer = entityManager.persistAndFlush(customer);
        customerRepository.delete(customer);
        assertFalse(customerRepository.findAll().contains(customer));
    }
}