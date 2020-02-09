package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.enums.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PatientRepositoryTest {


    public static final Long PATIENT_8_ID= 8L;
    public static final Long PATIENT_9_ID= 9L;
    public static final String PATIENT_EMAIL = "patijent@gmail.com";
    public static final Status PATIENT_STATUS_APPROVED = Status.APPROVED;
    public static final int NUMBER_OF_PATIENTS_WITH_STATUS_APPROVED= 5;


    @Autowired
    PatientRepository patientRepository;

    @Test
    public void findByEmail(){

        Patient patient = patientRepository.findByEmail(PATIENT_EMAIL);
        assertEquals(PATIENT_8_ID, patient.getId());

    }

    @Test
    public void findByStatus(){
        List<Patient> patients = patientRepository.findByStatus(PATIENT_STATUS_APPROVED);
        assertEquals(NUMBER_OF_PATIENTS_WITH_STATUS_APPROVED,patients.size());

        for(Patient patient :patients){
            assertEquals(PATIENT_STATUS_APPROVED, patient.getPatientStatus());
        }
    }

    @Test
    public void findOneById(){
        Patient p = patientRepository.findOneById(PATIENT_9_ID);
        assertEquals(PATIENT_9_ID, p.getId());
    }

    @Test
    public void findById(){
        Patient p = patientRepository.findOneById(PATIENT_9_ID);
        assertEquals(PATIENT_9_ID, p.getId());
    }
}
