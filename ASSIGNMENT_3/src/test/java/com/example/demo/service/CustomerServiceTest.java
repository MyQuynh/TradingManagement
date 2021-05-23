package com.example.demo.service;

import com.example.demo.exception.ResourcesNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import com.example.demo.repository.CustomerRepository;
import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    static abstract class TestClass {
        public abstract int booleanMethod(boolean arg);
    }


    @Test
    void findAll() {
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());
        customerService.saveAll(customerList);
        Mockito.when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> customerActual = customerService.findAll();
        assertEquals(customerActual.size(), customerList.size());
    }

    @Test
    void findCustomersById() {
        Customer customer = new Customer();
        customerService.save(customer);
        Mockito.when(customerRepository.findCustomersById(1L)).thenReturn(customer);
        Customer customerActual = customerService.findCustomersById(1L);
        assertEquals(customer, customerActual);
    }

    @Test
    void save() {

        Customer customer = new Customer();
        customer.setId(11L);
        // Customer customer1 = customerService.save(customer);
        // assertEquals(customer, customer1);
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
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
        customerService.deleteById(11L);
        Mockito.verify(customerRepository, times(1)).deleteById(customerTest.getId());
        assertNull(customerService.findCustomersById(11L));
    }

    @Test
    void existsById() {
    }

    @Test
    void updateCustomer() throws ResourcesNotFoundException {


        Customer customerBefore = new Customer();
        customerBefore.setFirstName("Test");

        Customer customerUpdated = new Customer();
        customerUpdated.setId(0L);
        customerUpdated.setFirstName("New test");

        when(customerRepository.findById(customerBefore.getId())).thenReturn(Optional.of(customerBefore));
        when(customerRepository.save(any(Customer.class))).thenReturn(customerBefore);

        Customer customerActual = customerService.updateCustomer(customerUpdated);

        assertEquals(customerUpdated.getFirstName(), customerActual.getFirstName());
        assertNotEquals("Test", customerActual.getFirstName());


    }

    @Test
    void findByFirstName() {
        String firstName = "firstname-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findByFirstName(firstName)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByFirstName(firstName);
        assertEquals(customerValidate.size(), customersActual.size());

    }

    @Test
    void findByLastName() {
        String lastName = "lastname-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findByLastName(lastName)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByLastName(lastName);
        assertEquals(customerValidate.size(), customersActual.size());
    }

    @Test
    void findByAddress() {
        String address = "address-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findCustomersByAddress(address)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByAddress(address);
        assertEquals(customerValidate.size(), customersActual.size());
    }

    @Test
    void findByPhone() {
        String phone = "phone-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findCustomersByPhone(phone)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByPhone(phone);
        assertEquals(customerValidate.size(), customersActual.size());
    }

    @Test
    void findByFax() {
        String fax = "fax-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findCustomersByFax(fax)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByFax(fax);
        assertEquals(customerValidate.size(), customersActual.size());
    }

    @Test
    void findByEmail() {
        String email = "email-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findCustomersByEmail(email)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByEmail(email);
        assertEquals(customerValidate.size(), customersActual.size());
    }

    @Test
    void findByContactPerson() {
        String contactPerson = "contact-0";
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        List<Customer> customerValidate = new ArrayList<>();
        customerValidate.add(customerList.get(0));
        customerService.saveAll(customerList);
        when(customerRepository.findCustomersByContactPerson(contactPerson)).thenReturn(customerValidate);
        List<Customer> customersActual = customerService.findByContactPerson(contactPerson);
        assertEquals(customerValidate.size(), customersActual.size());
    }
}