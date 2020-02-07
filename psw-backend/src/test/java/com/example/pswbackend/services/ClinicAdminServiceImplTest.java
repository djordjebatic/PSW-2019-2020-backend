package com.example.pswbackend.services;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.ClinicAdminRepository;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class ClinicAdminServiceImplTest {

    @Autowired
    ClinicAdminService clinicAdminService;

    @Mock
    AccountRepository accountRepositoryMock;

    @Mock
    ClinicAdminRepository clinicAdminRepository;

    @Mock
    ClinicRepository clinicRepository;

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    AppointmentRequestService appointmentRequestService;

    @Test
    public void register_whenGivenEmailAlreadyExists_returnsNull() {

        ClinicAdminDTO dto = new ClinicAdminDTO();
        dto.setEmail("cadmin1@gmail.com");
        ClinicAdmin found = new ClinicAdmin();

        when(accountRepositoryMock.findByEmail(dto.getEmail())).thenReturn(found);

        assertEquals(null, clinicAdminService.register(dto));

    }

    @Test
    public void register_withValidDtoObject_returnsClinicAdmin() {

        Clinic clinic = new Clinic();
        clinic.setId(17L);
        clinic.setName("Nova");

        ClinicAdminDTO dto = new ClinicAdminDTO("novica@gmail.com","Novica","Novic","061234567","Bulevar","Novi Sad","Srbija",clinic.getId());
        ClinicAdmin saved_and_returned = new ClinicAdmin();

        saved_and_returned.setEmail(dto.getEmail());
        saved_and_returned.setClinic(clinic);

        when(accountRepositoryMock.findByEmail(dto.getEmail())).thenReturn(null);
        when(clinicRepository.findOneById(dto.getClinicId())).thenReturn(clinic);
        when(clinicAdminRepository.save(saved_and_returned)).thenReturn(saved_and_returned);

        assertEquals(dto.getEmail(), clinicAdminService.register(dto).getUsername());

    }

    @Test
    public void receiveAppointmentRequest_withNonExistingDoctor_returnsFalse() {

        AppointmentDoctorDTO dto = new AppointmentDoctorDTO();
        dto.setPatient(8L);
        dto.setDoctor("99");
        dto.setType("0");
        when(doctorRepository.findById(Long.parseLong(dto.getDoctor()))).thenReturn(null);

        assertFalse(clinicAdminService.receiveAppointmentRequest(dto));

    }

    @Test
    public void receiveAppointmentRequest_withValidDto_returnsTrue() {

        AppointmentDoctorDTO dto = new AppointmentDoctorDTO();
        dto.setPatient(8L);
        dto.setDoctor("4");
        dto.setType("0");
        dto.setStartDateTime("2020-12-03 12:00");
        dto.setEndDateTime("2020-12-03 13:00");
        //when(doctorRepository.findById(Long.parseLong(dto.getDoctor()))).thenReturn(null);

        assertTrue(clinicAdminService.receiveAppointmentRequest(dto));

    }

}
