package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Staff;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void saveAndFindAll() {
        Staff staff = new Staff();
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        assertTrue(staffRepository.findAll().contains(staff));
        assertTrue(staffRepository.findAll().contains(staff1));
    }

    @Test
    void delete(){
        Staff staff = new Staff();
        staff = entityManager.persistAndFlush(staff);
        staffRepository.delete(staff);
        assertFalse(staffRepository.findAll().contains(staff));
    }

    @Test
    void findByFirstName() {
        Staff staff = new Staff();
        staff.setFirstName("TestFirstName");
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        staff1.setFirstName("Test1");
        staff1 = entityManager.persistAndFlush(staff1);

        assertTrue( staffRepository.findByFirstName("TestFirstName").contains(staff));
        assertFalse( staffRepository.findByFirstName("TestFirstName").contains(staff1));

    }

    @Test
    void findByLastName() {
        Staff staff = new Staff();
        staff.setLastName("Test");
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        staff1.setLastName("Test1");
        staff1 = entityManager.persistAndFlush(staff1);

        assertTrue( staffRepository.findByLastName("Test").contains(staff));
        assertTrue( staffRepository.findByLastName("Test").contains(staff1));
    }

    @Test
    void findStaffById() {
        Staff staff = new Staff();
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        staff1 = entityManager.persistAndFlush(staff1);

        assertEquals(staffRepository.findStaffById(staff.getId()), staff);
        assertNotEquals(staffRepository.findStaffById(staff.getId()), staff1);
    }


    @Test
    void findByAddress() {
        Staff staff = new Staff();
        staff.setAddress("Test");
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        staff1.setAddress("Test1");
        staff1 = entityManager.persistAndFlush(staff1);

        assertTrue( staffRepository.findByAddress("Test").contains(staff));
        assertFalse( staffRepository.findByAddress("Test").contains(staff1));
    }

    @Test
    void findByEmail() {
        Staff staff = new Staff();
        staff.setEmail("Test");
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        staff1.setEmail("Test1");
        staff1 = entityManager.persistAndFlush(staff1);

        assertTrue( staffRepository.findByEmail("Test").contains(staff));
        assertFalse( staffRepository.findByEmail("Test").contains(staff1));
    }

    @Test
    void findByPhone() {
        Staff staff = new Staff();
        staff.setPhone("Test");
        staff = entityManager.persistAndFlush(staff);

        Staff staff1 = new Staff();
        staff1.setPhone("Test1");
        staff1 = entityManager.persistAndFlush(staff1);

        assertTrue( staffRepository.findByPhone("Test").contains(staff));
        assertTrue( staffRepository.findByPhone("Test").contains(staff1));
    }

}