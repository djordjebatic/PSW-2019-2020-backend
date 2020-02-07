package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.dto.ClinicAdminDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class ClinicAdminRepositoryTest {

    @Autowired
    ClinicAdminRepository clinicAdminRepository;

    @Test
    public void findOneById_withValidId_returnsClinicAdmin() {

        Long clinicAdminId = 2L;

        ClinicAdmin ca = clinicAdminRepository.findOneById(clinicAdminId);
        assertEquals(clinicAdminId, ca.getId());
    }

    @Test
    public void findOneById_withNonExistingId_returnsClinicAdmin() {

        Long clinicAdminId = 222L;

        ClinicAdmin ca = clinicAdminRepository.findOneById(clinicAdminId);
        assertEquals(ca, null);
    }

    @Test
    public void findByClinicId_withValidId_returnsClinicAdminsList() {

        Long clinicId = 1L;

        List<ClinicAdmin> caList = clinicAdminRepository.findByClinicId(clinicId);
        assertEquals(1, caList.size());

        for (ClinicAdmin ca : caList){
            assertEquals(clinicId, ca.getClinic().getId());
        }

    }

    @Test
    public void findByClinicId_withNonExistingClinicId_returnsEmptyList() {

        Long clinicId = 17L;

        List<ClinicAdmin> caList = clinicAdminRepository.findByClinicId(clinicId);
        assertEquals(0, caList.size());

    }

    @Test
    public void findAll_returnsClinicAdminsList() {

        List<ClinicAdmin> caList = clinicAdminRepository.findAll();
        assertEquals(2, caList.size());

    }
}
