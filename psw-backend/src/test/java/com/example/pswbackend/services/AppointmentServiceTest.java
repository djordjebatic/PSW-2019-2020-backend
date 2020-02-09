package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentCalendarDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.*;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceTest {

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.FEBRUARY;

    public static final int DAY_OF_MONTH_START = 27;


    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;

    public static final Long DOCTOR_4_ID = 4L;

    public static final long ORDINATION_4_ID = 4L;

    @MockBean
    private ClinicRepository clinicRepository;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private NurseRepository nurseRepository;

    @MockBean
    private AppointmentRequestRepository appointmentRequestRepository;

    @MockBean
    private EmailService emailService;


    @Autowired
    private AppointmentService appointmentService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDoctorAppointments_Success() {

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
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);
        Appointment appointment2 = new Appointment(2L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_BOOKED, patient, nurse, startDateTime.plusHours(2), endDateTime.plusHours(2));

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        given(this.appointmentRepository.findByDoctorsIdAndStatusIn(DOCTOR_4_ID, statuses)).willReturn(appointments);

        List<AppointmentCalendarDTO> doctorAppointments = appointmentService.getDoctorAppointments(DOCTOR_4_ID);

        assertThat(doctorAppointments).hasSize(2);
    }

    @Test
    public void testGetNurseAppointments_Success() {

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
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);
        Appointment appointment2 = new Appointment(2L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_BOOKED, patient, nurse, startDateTime.plusHours(2), endDateTime.plusHours(2));

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        given(this.appointmentRepository.findByNurseIdAndStatusIn(6L, statuses)).willReturn(appointments);
        List<AppointmentCalendarDTO> doctorAppointments = appointmentService.getNurseAppointments(6L);
        assertThat(doctorAppointments).hasSize(2);
    }

    @Test
    public void testGetOrdinationAppointmentsDuringTheDay_Next_Day() {

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        Clinic clinic = new Clinic();
        clinic.setId(1L);

        Patient patient = new Patient();
        patient.setId(5L);
        Nurse nurse = new Nurse();
        nurse.setId(6L);

        ArrayList<Appointment> appointments = new ArrayList<>();

        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);

        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setId(1L);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.OPERATION);

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(1L);

        appointmentPrice.setAppointmentType(appointmentType);
        appointment1.setPrice(appointmentPrice);
        appointment1.setStartDateTime(startDateTime);
        appointment1.setEndDateTime(endDateTime);

        Ordination ord = new Ordination();
        ord.setId(ORDINATION_4_ID);
        ord.setClinic(clinic);
        appointment1.setOrdination(ord);

        Doctor d = new Doctor();
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doclist = new HashSet<>();
        doclist.add(d);
        appointment1.setDoctors(doclist);
        appointment1.setStatus(AppointmentStatus.APPROVED);
        appointment1.setPatient(patient);
        appointment1.setNurse(nurse);

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);

        appointments.add(appointment1);

        given(this.appointmentRepository.findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(ORDINATION_4_ID, startDateTime, endDateTime, statuses)).willReturn(appointments);

        List<Appointment> returnedAppointment = appointmentService.getOrdinationAppointmentsDuringTheDay(ORDINATION_4_ID, startDateTime.plusDays(1));
        assertThat(returnedAppointment).hasSize(0);
    }

    //fails
    @Test
    public void testGetDoctorAppointmentsDuringTheDay_Success() {
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

        Doctor doctor = new Doctor();
        doctor.setId(DOCTOR_4_ID);
        doctor.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(doctor);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);
        Appointment appointment2 = new Appointment(2L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.PREDEF_BOOKED, patient, nurse, startDateTime.plusHours(2), endDateTime.plusHours(2));

        ArrayList<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        LocalDate date = startDateTime.toLocalDate();

        LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0));
        LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

        given(this.appointmentRepository.findByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn(DOCTOR_4_ID, start, end, statuses)).willReturn(appointments);

        List<Appointment> doctorAppointments = appointmentService.getDoctorAppointmentsDuringTheDay(DOCTOR_4_ID, startDateTime);

        assertThat(doctorAppointments).hasSize(2);
    }

    @Test
    @Transactional
    public void testAssignOrdinationOperation_Success() {

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
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);

        given(this.appointmentRepository.save(appointment1)).willReturn(appointment1);

        Appointment assigned = appointmentService.assignOperationOrdination(appointment1, ord, doctors);

        assertNotNull(assigned);
        assertEquals(ORDINATION_4_ID, assigned.getOrdination().getId());
    }

    @Test
    @Transactional
    public void testAssignOrdinationOperation_No_Doctors() {

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

        Set<Doctor> doctors = new HashSet<>();
        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);

        given(this.appointmentRepository.save(appointment1)).willReturn(appointment1);

        Throwable exception = assertThrows(ValidationException.class, () -> appointmentService.assignOperationOrdination(appointment1, ord, doctors));
        assertEquals("You must assign at least one doctor before assigning the ordination", exception.getMessage());
    }

    @Test
    public void testCancelAppointmentP_Success() {

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
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);

        Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString());
        given(appointmentRepository.getByIdAndStatusNot(appointment1.getId(), AppointmentStatus.CANCELED)).willReturn(appointment1);
        given(appointmentRepository.save(appointment1)).willReturn(appointment1);

        Appointment returned = appointmentService.cancelAppointmentP(appointment1.getId());

        assertNotNull(returned);
        assertEquals(returned.getStatus().toString(), "CANCELED");
    }

    @Test
    public void testCancelAppointmentP_Date_Before_Now() {

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
        d.setId(DOCTOR_4_ID);
        d.setClinic(clinic);
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(d);

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.APPROVED, patient, nurse, startDateTime, endDateTime);

        given(appointmentRepository.getByIdAndStatusNot(appointment1.getId(), AppointmentStatus.CANCELED)).willReturn(appointment1);

        Appointment returned = appointmentService.cancelAppointmentP(appointment1.getId());

        assertNull(returned);
    }
}