package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import com.example.demo.service.CustomerService;
import com.example.demo.service.StaffService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class StaffControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StaffService staffService;

    ObjectMapper mapper  = new ObjectMapper();

    private final Staff staff = new Staff();

    @Before
    public void setup() {
        staffService.save(this.staff);
    }

    @After
    public void cleanup() {
        staffService.deleteById(staff.getId());
    }

    @Test
    void findAll() throws Exception {
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());

        given(staffService.findAll()).willReturn(staffList);

        mvc.perform(get("/api/v1/staffs").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].firstName", is("firstname-0")))
                .andExpect(jsonPath("$[0].lastName", is("lastname-0")))
                .andExpect(jsonPath("$[0].address", is("address-0")))
                .andExpect(jsonPath("$[0].phone", is("phone-0")))
                .andExpect(jsonPath("$[0].email", is("email-0")))
        ;
    }

    @Test
    void findStaffById() throws Exception {
        int staffId = 0;
        Staff staff = new Staff(staffId,"firstname-" + staffId, "lastname-" + staffId, "address-"+staffId,"phone-"+staffId,"email-"+staffId);
        given(staffService.findStaffById((long) staffId)).willReturn(staff);

        mvc.perform(get("/api/v1/staffs/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.firstName", is("firstname-0")))
                .andExpect(jsonPath("$.lastName", is("lastname-0")))
                .andExpect(jsonPath("$.address", is("address-0")))
                .andExpect(jsonPath("$.phone", is("phone-0")))
                .andExpect(jsonPath("$.email", is("email-0")))
        ;
    }

    @Test
    void createStaff() throws Exception {
        int staffId = 66;
        Staff staff = new Staff();
        staff.setFirstName("firstname-"+staffId);
        staff.setLastName("lastname-"+staffId);
        staff.setAddress("address-"+staffId);
        staff.setPhone("phone-"+staffId);
        staff.setEmail("email-"+staffId);
        given(staffService.save(any(Staff.class))).willReturn(staff);
        mvc.perform(post("/api/v1/staffs/add")
                .content(mapper.writeValueAsString(staff)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstname-"+staffId)))
                .andExpect(jsonPath("$.lastName", is("lastname-"+staffId)))
                .andExpect(jsonPath("$.address", is("address-"+staffId)))
                .andExpect(jsonPath("$.phone", is("phone-"+staffId)))
                .andExpect(jsonPath("$.email", is("email-"+staffId)))
        ;
    }

    @Test
    void updateStaff() throws Exception {
        int staffId = 66;
        Staff staff = new Staff();
        staff.setFirstName("firstname-"+staffId);
        staff.setLastName("lastname-"+staffId);
        staff.setAddress("address-"+staffId);
        staff.setPhone("phone-"+staffId);
        staff.setEmail("email-"+staffId);
        given(staffService.updateStaff(any(Staff.class))).willReturn(staff);
        mvc.perform(put("/api/v1/staffs/{id}", staff.getId())
                .content(mapper.writeValueAsString(staff)) // Generate java object into Json
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("firstname-"+staffId)))
                .andExpect(jsonPath("$.lastName", is("lastname-"+staffId)))
                .andExpect(jsonPath("$.address", is("address-"+staffId)))
                .andExpect(jsonPath("$.phone", is("phone-"+staffId)))
                .andExpect(jsonPath("$.email", is("email-"+staffId)))
        ;
    }

    @Test
    void deleteStaff() throws Exception {
        Mockito.when(staffService.deleteById(0L)).thenReturn("SUCCESS");
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/staffs/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDataByFirstName() throws Exception {
        String firstName = "firstname-0";
        List<Staff> staffList = new ArrayList<>();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        staff.setFirstName(firstName);
        staff1.setFirstName(firstName);
        staff.setLastName("test1");
        staff1.setLastName("test12");

        staffList.add(staff);
        staffList.add(staff1);

        when(staffService.findByFirstName(firstName)).thenReturn(staffList);
        mvc.perform(get("/api/v1/staffs/searchFirstName")
                .param("firstname", firstName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is(firstName)))
                .andExpect(jsonPath("$[1].firstName", is(firstName))
                );
    }

    @Test
    void fetchDataByLastName() throws Exception {
        String lastName = "lastname-0";
        List<Staff> staffList = new ArrayList<>();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        staff.setLastName(lastName);
        staff1.setLastName(lastName);
        staff.setFirstName("test1");
        staff1.setFirstName("test12");

        staffList.add(staff);
        staffList.add(staff1);

        when(staffService.findByLastName(lastName)).thenReturn(staffList);
        mvc.perform(get("/api/v1/staffs/searchLastName")
                .param("lastname", lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName", is(lastName)))
                .andExpect(jsonPath("$[1].lastName", is(lastName))
                );
    }


    @Test
    void fetchDataByAddress() throws Exception {
        String address = "address-0";
        List<Staff> staffList = new ArrayList<>();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        staff.setAddress(address);
        staff1.setAddress(address);
        staff.setFirstName("test1");
        staff1.setFirstName("test12");

        staffList.add(staff);
        staffList.add(staff1);

        when(staffService.findByAddress(address)).thenReturn(staffList);
        mvc.perform(get("/api/v1/staffs/searchAddress")
                .param("address", address))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is(address)))
                .andExpect(jsonPath("$[1].address", is(address))
                );
    }

    @Test
    void fetchDataByPhone() throws Exception {
        String phone = "phone-0";
        List<Staff> staffList = new ArrayList<>();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        staff.setPhone(phone);
        staff1.setPhone(phone);
        staff.setFirstName("test1");
        staff1.setFirstName("test12");

        staffList.add(staff);
        staffList.add(staff1);

        when(staffService.findByPhone(phone)).thenReturn(staffList);
        mvc.perform(get("/api/v1/staffs/searchPhone")
                .param("phone", phone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].phone", is(phone)))
                .andExpect(jsonPath("$[1].phone", is(phone))
                );

    }

    @Test
    void fetchDataByEmail() throws Exception {
        String email = "email-0";
        List<Staff> staffList = new ArrayList<>();
        Staff staff = new Staff();
        Staff staff1 = new Staff();
        staff.setEmail(email);
        staff1.setEmail(email);
        staff.setFirstName("test1");
        staff1.setFirstName("test12");

        staffList.add(staff);
        staffList.add(staff1);

        when(staffService.findByEmail(email)).thenReturn(staffList);
        mvc.perform(get("/api/v1/staffs/searchEmail")
                .param("email", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", is(email)))
                .andExpect(jsonPath("$[1].email", is(email))
                );
    }
}