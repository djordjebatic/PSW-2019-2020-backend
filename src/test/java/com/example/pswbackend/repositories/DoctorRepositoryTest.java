package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void findByClinicId_withValidId_returnsDoctorList() {

        Long clinicId = 1L;

        List<Doctor> doctorList = doctorRepository.findByClinicId(clinicId);
        assertEquals(7, doctorList.size());

        for (Doctor d : doctorList){
            assertEquals(clinicId, d.getClinic().getId());
        }

    }

    @Test
    public void findByClinicId_withNonExistingClinicId_returnsEmptyList() {

        Long clinicId = 17L;

        List<Doctor> doctorList = doctorRepository.findByClinicId(clinicId);
        assertEquals(0, doctorList.size());

    }

    @Test
    public void findByEmail_withValidEmail_returnsDoctor() {

        String email = "dok@gmail.com";

        Doctor doctor = doctorRepository.findByEmail(email);
        assertEquals("dok@gmail.com", doctor.getUsername());

    }

    @Test
    public void findByEmail_withNonExistingEmail_returnsNull() {

        String email = "dog@gmail.com";

        Doctor doctor = doctorRepository.findByEmail(email);
        assertEquals(doctor,null);

    }

    @Test
    public void findByEmail_withEmptyString_returnsNull() {

        String email = "";

        Doctor doctor = doctorRepository.findByEmail(email);
        assertEquals(doctor,null);

    }

    @Test
    public void findAll_returnsDoctorsList() {

        List<Doctor> doctorList = doctorRepository.findAll();
        assertEquals(14, doctorList.size());

    }

    @Test
    public void deleteOneById_withValidId_throwsPSQLEsception() {

        Long doctorId = 4L;
        //assertTrue(doctorRepository.deleteOneById(doctorId)); // assert that throws PSQLException

    }

    @Test
    public void deleteOneById_withNonExistingId_throwsPSQLEsception() {

        Long doctorId = 14L;
        //assertFalse(doctorRepository.deleteOneById(doctorId)); // assert that throws PSQLException

    }

    @Test
    public void findByClinicIdAndSpecializationId_withValidIds_returnsListOfDoctors() {

        Long clinicId = 1L;
        Long specializationId = 1L;
        List<Doctor> doctors = doctorRepository.findByClinicIdAndSpecializationId(clinicId, specializationId);

        assertEquals(1, doctors.size());

        for (Doctor d:doctors){
            assertEquals(clinicId, d.getClinic().getId());
        }

        for (Doctor d:doctors){
            assertEquals(specializationId, d.getSpecialization().getId());
        }
    }

    @Test
    public void findByClinicIdAndSpecializationId_withNonExistingClinicId_returnsListOfDoctors() {

        Long clinicId = 101L;
        Long specializationId = 1L;
        List<Doctor> doctors = doctorRepository.findByClinicIdAndSpecializationId(clinicId, specializationId);

        assertEquals(0, doctors.size());

    }

    @Test
    public void findByClinicIdAndSpecializationId_withNonExistingSpecializationId_returnsListOfDoctors() {

        Long clinicId = 1L;
        Long specializationId = 101L;
        List<Doctor> doctors = doctorRepository.findByClinicIdAndSpecializationId(clinicId, specializationId);

        assertEquals(0, doctors.size());

    }

    @Test
    public void findByWorkTimeStartGreaterThanEqualAndWorkTimeEndLowerThan_withExistingDoctorsDuringThatWorkingTime_returnsListOfDoctors() {

        LocalTime start = LocalTime.of(10,0);
        LocalTime end = LocalTime.of(16,0);

        //TODO dodaj radno vreme u bazu

        List<Doctor> doctors = doctorRepository.findByWorkTimeStartGreaterThanEqualAndWorkTimeEndLowerThan(start, end, 1L);
        assertEquals(1, doctors.size());

        for (Doctor d:doctors){
            assertTrue(d.getWorkTimeStart().isBefore(start));
            assertTrue(d.getWorkTimeEnd().isAfter(end));
        }

    }

    @Test
    public void findByWorkTimeStartGreaterThanEqualAndWorkTimeEndLowerThan_withNonExistingDoctorsDuringThatWorkingTime_returnsListOfDoctors() {

        LocalTime start = LocalTime.of(1,0);
        LocalTime end = LocalTime.of(2,0);

        List<Doctor> doctors = doctorRepository.findByWorkTimeStartGreaterThanEqualAndWorkTimeEndLowerThan(start, end, 1L);
        assertEquals(0, doctors.size());

    }

}
