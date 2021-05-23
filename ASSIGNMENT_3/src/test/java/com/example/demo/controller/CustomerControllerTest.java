package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    ObjectMapper mapper  = new ObjectMapper();

    private final Customer customer = new Customer();

    @Before
    public void setup() {
        customerService.save(this.customer);
    }

    @After
    public void cleanup() {
        customerService.deleteById(customer.getId());
    }


    @Test
    public void findAllCustomer() throws Exception {
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());

        given(customerService.findAll()).willReturn(customerList);

        mvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].firstName", is("firstname-0")))
                .andExpect(jsonPath("$[0].lastName", is("lastname-0")))
                .andExpect(jsonPath("$[0].address", is("address-0")))
                .andExpect(jsonPath("$[0].phone", is("phone-0")))
                .andExpect(jsonPath("$[0].fax", is("fax-0")))
                .andExpect(jsonPath("$[0].email", is("email-0")))
                .andExpect(jsonPath("$[0].contactPerson", is("contact-0")))
        ;
    }

    @Test
    public void findCustomerById() throws Exception {
        int customerId = 0;
        Customer customer = new Customer(customerId,"firstname-" + customerId, "lastname-" + customerId, "address-"+customerId,"phone-"+customerId,"fax-"+customerId,"email-"+customerId,"contact-"+customerId);

        given(customerService.findCustomersById((long) customerId)).willReturn(customer);

        mvc.perform(get("/api/v1/customers/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.firstName", is("firstname-0")))
                .andExpect(jsonPath("$.lastName", is("lastname-0")))
                .andExpect(jsonPath("$.address", is("address-0")))
                .andExpect(jsonPath("$.phone", is("phone-0")))
                .andExpect(jsonPath("$.fax", is("fax-0")))
                .andExpect(jsonPath("$.email", is("email-0")))
                .andExpect(jsonPath("$.contactPerson", is("contact-0")))
        ;

    }

    @Test
    public void createCustomer() throws Exception {
        int customerId = 66;
        Customer customer = new Customer();
        customer.setFirstName("firstname-"+customerId);
        customer.setLastName("lastname-"+customerId);
        customer.setAddress("address-"+customerId);
        customer.setPhone("phone-"+customerId);
        customer.setFax("fax-"+ customerId);
        customer.setEmail("email-"+customerId);
        customer.setContactPerson("contact-"+customerId);
        given(customerService.save(any(Customer.class))).willReturn(customer);
        mvc.perform(post("/api/v1/customers/add")
                .content(mapper.writeValueAsString(customer)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstname-"+customerId)))
                .andExpect(jsonPath("$.lastName", is("lastname-"+customerId)))
                .andExpect(jsonPath("$.address", is("address-"+customerId)))
                .andExpect(jsonPath("$.phone", is("phone-"+customerId)))
                .andExpect(jsonPath("$.fax", is("fax-"+customerId)))
                .andExpect(jsonPath("$.email", is("email-"+customerId)))
                .andExpect(jsonPath("$.contactPerson", is("contact-"+customerId)))
        ;
    }

    @Test
    public void updateCustomer() {

    }

    @Test
    public void deleteCustomer() throws Exception {
        Mockito.when(customerService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    public void fetchDataByFirstName() throws Exception {
        String firstName = "firstname-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setFirstName(firstName);
        customer1.setFirstName(firstName);
        customer.setLastName("test1");
        customer1.setLastName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByFirstName(firstName)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchFirstName")
                .param("firstname", firstName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(firstName)))
                .andExpect(jsonPath("$[1].firstName", is(firstName))
                );
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").exists())
//                .andExpect(jsonPath("$[0].firstName").value("Jack"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().contentType("application/json"))
//                .andExpect(jsonPath("$[0].id").exists())
//                .andExpect(jsonPath("$[0].firstname").value("Marge"));;
    }

    @Test
    public void fetchDataByLastName() throws Exception {
        String lastName = "lastname-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setLastName(lastName);
        customer1.setLastName(lastName);
        customer.setFirstName("test1");
        customer1.setFirstName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByLastName(lastName)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchLastName")
                .param("lastname", lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName", is(lastName)))
                .andExpect(jsonPath("$[1].lastName", is(lastName))
                );
    }

    @Test
    public void fetchDataByAddress() throws Exception {
        String address = "address-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setAddress(address);
        customer1.setAddress(address);
        customer.setFirstName("test1");
        customer1.setFirstName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByAddress(address)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchAddress")
                .param("address", address))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is(address)))
                .andExpect(jsonPath("$[1].address", is(address))
                );
    }

    @Test
    public void fetchDataByPhone() throws Exception {
        String phone = "phone-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setPhone(phone);
        customer1.setPhone(phone);
        customer.setFirstName("test1");
        customer1.setFirstName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByPhone(phone)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchPhone")
                .param("phone", phone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].phone", is(phone)))
                .andExpect(jsonPath("$[1].phone", is(phone))
                );

    }

    @Test
    public void fetchDataByFax() throws Exception {
        String fax = "fax-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setFax(fax);
        customer1.setFax(fax);
        customer.setFirstName("test1");
        customer1.setFirstName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByFax(fax)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchFax")
                .param("fax", fax))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fax", is(fax)))
                .andExpect(jsonPath("$[1].fax", is(fax))
                );

    }

    @Test
    public void fetchDataByEmail() throws Exception {
        String email = "email-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setEmail(email);
        customer1.setEmail(email);
        customer.setFirstName("test1");
        customer1.setFirstName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByEmail(email)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchEmail")
                .param("email", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", is(email)))
                .andExpect(jsonPath("$[1].email", is(email))
                );

    }

    @Test
    public void fetchDataByContactPerson() throws Exception {
        String contactPerson = "contact-0";
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setContactPerson(contactPerson);
        customer1.setContactPerson(contactPerson);
        customer.setFirstName("test1");
        customer1.setFirstName("test12");

        customerList.add(customer);
        customerList.add(customer1);

        when(customerService.findByContactPerson(contactPerson)).thenReturn(customerList);
        mvc.perform(get("/api/v1/customers/searchContactPerson")
                .param("contactPerson", contactPerson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contactPerson", is(contactPerson)))
                .andExpect(jsonPath("$[1].contactPerson", is(contactPerson))
                );
    }

    @Test
    public void getAllEmployees() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setLastName("test123");
        //System.out.println(customer);

        //System.out.println(customerService.save(customer));

        Pageable paging = PageRequest.of(0, 1, Sort.by("id"));
        // System.out.println(customerService.save(new Customer()));
        System.out.println(customerService.findAll());
        List<Customer> pageOne = customerService.getAllEmployees(0,1,"id");
        List<Customer> customerList = IntStream.range(0, 10)
                .mapToObj(i -> new Customer(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"fax-"+i,"email-"+i,"contact-"+i))
                .collect(Collectors.toList());
        for (Customer customer1 : customerList) {
            customerService.save(customer1);
        }

        System.out.println(customerService.findAll());


        given(customerService.save(any(Customer.class))).willReturn(customer);
        assertNotNull(pageOne);
        assertEquals(1, pageOne.size());


    }
}