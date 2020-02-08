package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.repositories.OrdinationRepository;
import com.example.pswbackend.repositories.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrdinationServiceTest {

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.FEBRUARY;

    public static final int DAY_OF_MONTH_START = 27;

    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;

    public static final Long DOCTOR_4_ID = 4L;

    public static final long ORDINATION_4_ID = 4L;

    @Autowired
    OrdinationService ordinationService;

    @MockBean
    private ClinicRepository clinicRepository;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private OrdinationRepository ordinationRepository;

    @MockBean
    private EmailService emailService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllOrdinationsInClinic() {
    }

    @Test
    @Transactional
    public void assignOrdinationForOperation_Success() {

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        Patient patient = new Patient();
        patient.setId(5L);
        Nurse nurse = new Nurse();
        nurse.setId(6L);

        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setId(1L);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.OPERATION);

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(1L);

        appointmentPrice.setAppointmentType(appointmentType);

        Clinic clinic = new Clinic();
        clinic.setId(1L);

        Ordination ordination = new Ordination();
        ordination.setId(ORDINATION_4_ID);
        ordination.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ordination, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);
        appointment1.setStatus(AppointmentStatus.APPROVED);

        Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());
        given(this.appointmentService.getAppointment(1L)).willReturn(appointment1);
        given(this.ordinationRepository.findOneById(ORDINATION_4_ID)).willReturn(ordination);

        Appointment assigned = ordinationService.assignOrdinationForOperation(1L, ORDINATION_4_ID, doctors);

        assertNotNull(assigned);
        assertEquals(ORDINATION_4_ID, assigned.getOrdination().getId());


        verify(appointmentService, times(1)).getAppointment(1L);
        verify(ordinationRepository, times(1)).findOneById(ORDINATION_4_ID);
        verify(appointmentService, times(1)).getOrdinationAppointmentsDuringTheDay(ORDINATION_4_ID, startDateTime);
        verify(appointmentService, times(1)).assignOperationOrdination(appointment1, ordination, doctors);
        verify(ordinationRepository, times(1)).findOneById(ORDINATION_4_ID);
    }

    @Test
    @Transactional
    public void testAssignOrdinationForOperation_Not_Operation() {

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        Patient patient = new Patient();
        patient.setId(5L);
        Nurse nurse = new Nurse();
        nurse.setId(6L);

        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setId(1L);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.EXAMINATION);

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(1L);

        appointmentPrice.setAppointmentType(appointmentType);

        Clinic clinic = new Clinic();
        clinic.setId(1L);

        Ordination ordination = new Ordination();
        ordination.setId(ORDINATION_4_ID);
        ordination.setClinic(clinic);

        Ordination ordination2 = new Ordination();
        ordination2.setId(ORDINATION_4_ID+1);
        ordination2.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_4_ID);
        Doctor d2 = new Doctor();
        d2.setId(DOCTOR_4_ID + 1);
        d2.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);
        doctors.add(d2);

        Set<Doctor> doctors2 = new HashSet<>();
        doctors2.add(d2);


        Appointment appointment1 = new Appointment(1L, appointmentPrice, ordination, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);

        Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());
        given(this.appointmentService.getAppointment(1L)).willReturn(appointment1);
        given(this.ordinationRepository.findOneById(ORDINATION_4_ID)).willReturn(ordination);

        Throwable exception = assertThrows(ValidationException.class, () -> ordinationService.assignOrdinationForOperation(1L, ORDINATION_4_ID, doctors));
        assertEquals("Appointment type can not be 'Examination'!", exception.getMessage());
    }

    @Test
    public void testIsOrdinationAvailable_False(){
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        Patient patient = new Patient();
        patient.setId(5L);
        Nurse nurse = new Nurse();
        nurse.setId(6L);

        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setId(1L);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.EXAMINATION);

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(1L);

        appointmentPrice.setAppointmentType(appointmentType);

        Clinic clinic = new Clinic();
        clinic.setId(1L);

        Ordination ordination = new Ordination();
        ordination.setId(ORDINATION_4_ID);
        ordination.setClinic(clinic);

        Ordination ordination2 = new Ordination();
        ordination2.setId(ORDINATION_4_ID+1);
        ordination2.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_4_ID);
        Doctor d2 = new Doctor();
        d2.setId(DOCTOR_4_ID + 1);
        d2.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);
        doctors.add(d2);

        Set<Doctor> doctors2 = new HashSet<>();
        doctors2.add(d2);


        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setStartDateTime(startDateTime);
        appointment1.setEndDateTime(endDateTime);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        ordination.setAppointments(appointments);

        given(this.appointmentService.getOrdinationAppointmentsDuringTheDay(ORDINATION_4_ID, startDateTime)).willReturn(appointments);

        assertEquals(true, this.ordinationService.isOrdinationAvailable(ordination, startDateTime, endDateTime));
    }
}