package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.StaffRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @InjectMocks
    StaffService staffService;

    @Mock
    StaffRepository staffRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());
        staffService.saveAll(staffList);
        Mockito.when(staffRepository.findAll()).thenReturn(staffList);
        List<Staff> staffActual = staffService.findAll();
        assertEquals(staffActual.size(), staffList.size());
    }

    @Test
    void findStaffById() {
        Staff staff = new Staff();
        staffService.save(staff);
        Mockito.when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        Staff staffActual = staffService.findStaffById(1L);
        assertEquals(staff, staffActual);
    }

    @Test
    void save() {
        Staff staff = new Staff();
        staff.setId(11L);
        // Customer customer1 = customerService.save(customer);
        // assertEquals(customer, customer1);
        Mockito.when(staffRepository.save(Mockito.any(Staff.class))).thenReturn(staff);
        Long staffId = staffService.save(staff).getId();
        assertEquals(11, staffId);
    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteById() {
        Staff staffTest = new Staff();
        staffTest.setId(11L);
        staffService.save(staffTest);
        staffService.deleteById(11L);
        Mockito.verify(staffRepository, times(1)).deleteById(staffTest.getId());
        assertNull(staffService.findStaffById(11L));
    }

    @Test
    void existsById() {
    }

    @Test
    void updateStaff() {
        Staff staffBefore = new Staff();
        staffBefore.setFirstName("Test");

        Staff staffUpdated = new Staff();
        staffUpdated.setId(0L);
        staffUpdated.setFirstName("New test");

        when(staffRepository.findById(staffBefore.getId())).thenReturn(Optional.of(staffBefore));
        when(staffRepository.save(any(Staff.class))).thenReturn(staffBefore);

        Staff staffActual = staffService.updateStaff(staffUpdated);

        assertEquals(staffUpdated.getFirstName(), staffActual.getFirstName());
        assertNotEquals("Test", staffActual.getFirstName());
    }

    @Test
    void findByFirstName() {
        String firstName = "firstname-0";
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());

        List<Staff> staffValidate = new ArrayList<>();
        staffValidate.add(staffList.get(0));
        staffService.saveAll(staffList);
        when(staffRepository.findByFirstName(firstName)).thenReturn(staffValidate);
        List<Staff> staffsActual = staffService.findByFirstName(firstName);
        assertEquals(staffValidate.size(), staffsActual.size());
    }

    @Test
    void findByLastName() {
        String lastName = "lastname-0";
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());

        List<Staff> staffValidate = new ArrayList<>();
        staffValidate.add(staffList.get(0));
        staffService.saveAll(staffList);
        when(staffRepository.findByLastName(lastName)).thenReturn(staffValidate);
        List<Staff> staffsActual = staffService.findByLastName(lastName);
        assertEquals(staffValidate.size(), staffsActual.size());
    }

    @Test
    void findByAddress() {
        String address = "address-0";
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());

        List<Staff> staffValidate = new ArrayList<>();
        staffValidate.add(staffList.get(0));
        staffService.saveAll(staffList);
        when(staffRepository.findByAddress(address)).thenReturn(staffValidate);
        List<Staff> staffsActual = staffService.findByAddress(address);
        assertEquals(staffValidate.size(), staffsActual.size());
    }

    @Test
    void findByEmail() {
        String email = "email-0";
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());

        List<Staff> staffValidate = new ArrayList<>();
        staffValidate.add(staffList.get(0));
        staffService.saveAll(staffList);
        when(staffRepository.findByEmail(email)).thenReturn(staffValidate);
        List<Staff> staffsActual = staffService.findByEmail(email);
        assertEquals(staffValidate.size(), staffsActual.size());
    }

    @Test
    void findByPhone() {
        String phone = "phone-0";
        List<Staff> staffList = IntStream.range(0, 10)
                .mapToObj(i -> new Staff(i,"firstname-" + i, "lastname-" + i, "address-"+i,"phone-"+i,"email-"+i))
                .collect(Collectors.toList());

        List<Staff> staffValidate = new ArrayList<>();
        staffValidate.add(staffList.get(0));
        staffService.saveAll(staffList);
        when(staffRepository.findByPhone(phone)).thenReturn(staffValidate);
        List<Staff> staffsActual = staffService.findByPhone(phone);
        assertEquals(staffValidate.size(), staffsActual.size());
    }
}