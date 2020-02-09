package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.PredefinedAppointmentDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PredefinedAppointmentServiceTest {


    @Autowired
    PredefinedAppointmentService predefinedAppointmentService;

    @MockBean
    AppointmentRepository appointmentRepository;

    @MockBean
    PatientRepository patientRepository;

    public static final int YEAR = 2020;
    public static final Month MONTH_DATE = Month.FEBRUARY;
    public static final Month MONTH_DATE1 = Month.MARCH;
    public static final Month MONTH_DATE2 = Month.OCTOBER;
    public static final int DAY_OF_MONTH_START = 27;
    public static final int START_TIME_HOUR = 10;
    public static final int END_TIME_HOUR = 11;
    public static final int MIN = 00;
    public static final int SEC = 00;
    public static final Long DOCTOR_7_ID = 7L;
    public static final long ORDINATION_4_ID = 4L;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_findById_success(){

        Appointment a = new Appointment();
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

        Ordination ord = new Ordination();
        ord.setId(ORDINATION_4_ID);
        ord.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_7_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_AVAILABLE, patient, nurse, startDateTime, endDateTime);

        given(this.appointmentRepository.findOneById(1L)).willReturn(appointment1);

        Appointment predA= predefinedAppointmentService.findById(1L);
        assertEquals(AppointmentStatus.PREDEF_AVAILABLE, predA.getStatus());
    }

    @Test
    public void test_findPredefinedByClinicId_success(){

        Appointment a = new Appointment();
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);
        LocalDateTime startDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);
        LocalDateTime startDateTime2 = LocalDateTime.of(YEAR, MONTH_DATE2, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime2 = LocalDateTime.of(YEAR, MONTH_DATE2, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

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

        Ordination ord = new Ordination();
        ord.setId(ORDINATION_4_ID);
        ord.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_7_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_BOOKED, patient, nurse, startDateTime, endDateTime);
        Appointment appointment2 = new Appointment(2L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_AVAILABLE, patient, nurse, startDateTime1, endDateTime1);
        Appointment appointment3 = new Appointment(3L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_AVAILABLE, patient, nurse, startDateTime2, endDateTime2);

        appointment1.setDiscount(5);
        appointment2.setDiscount(6);
        appointment3.setDiscount(7);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment2);
        appointments.add(appointment3);

        given(this.appointmentRepository.findByClinicIdAndStatus(1L, AppointmentStatus.PREDEF_AVAILABLE)).willReturn(appointments);

        List<PredefinedAppointmentDTO> predefinedAppointmentDTOS= new ArrayList<>();

        predefinedAppointmentDTOS = predefinedAppointmentService.findPredefinedByClinicId(1);

        assertThat(predefinedAppointmentDTOS).hasSize(2);

    }

    @Test
    public void test_findPredefinedByClinicId_Without_Any(){

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

        Ordination ord = new Ordination();
        ord.setId(ORDINATION_4_ID);
        ord.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_7_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        List<Appointment> appointments = new ArrayList<>();

        given(this.appointmentRepository.findByClinicIdAndStatus(1L, AppointmentStatus.PREDEF_AVAILABLE)).willReturn(appointments);

        List<PredefinedAppointmentDTO> predefinedAppointmentDTOS= new ArrayList<>();

        predefinedAppointmentDTOS = predefinedAppointmentService.findPredefinedByClinicId(1);

        assertThat(predefinedAppointmentDTOS).hasSize(0);

    }

    @Test
    public void findAll(){

    }

    @Test
    public void test_schedulePredefinedAppointment_success(){

        Appointment a = new Appointment();
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);
        LocalDateTime startDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime1 = LocalDateTime.of(YEAR, MONTH_DATE1, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

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

        Ordination ord = new Ordination();
        ord.setId(ORDINATION_4_ID);
        ord.setClinic(clinic);

        Doctor d = new Doctor();
        d.setId(DOCTOR_7_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_AVAILABLE, patient, nurse, startDateTime, endDateTime);
        Appointment appointment2 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_AVAILABLE, patient, nurse, startDateTime, endDateTime);
        appointment1.setDiscount(10);
        appointment2.setDiscount(8);

        Set<Appointment> appointments= new HashSet<>();
        appointments.add(appointment2);
        patient.setAppointments(appointments);

        PredefinedAppointmentDTO dto = predefinedAppointmentService.schedulePredefinedAppointment(patient, appointment1);
        assertThat(dto).isNotNull();
    }
}
