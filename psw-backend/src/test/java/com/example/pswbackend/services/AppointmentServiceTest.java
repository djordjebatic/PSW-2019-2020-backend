package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentCalendarDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.OrdinationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentServiceTest {

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.JANUARY;

    public static final int DAY_OF_MONTH_START = 27;

    public static final int DAY_OF_MONTH_END = 29;

    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;

    public static final String NEW_CLINIC_NAME = "Clinic";

    public static final String NEW_CLINIC_DESCRIPTION = "Best Clinic Ever";

    public static final String NEW_CLINIC_ADDRESS = "Balzakova 111";

    public static final String NEW_CLINIC_CITY = "Novi Sad";

    public static final Long CLINIC_20_ID = 20L;

    public static final Long ORDINATION_20_ID = 20L;

    public static final Long APPOINTMENT_20_ID = 20L;

    public static final Long DOCTOR_20_ID = 20L;

    public static final Long DOCTOR_4_ID = 4L;

    public static final int DOCTOR_4_APP_APPROVED_OR_PREDEF_BOOKED_COUNT = 5;

    public static final Long NURSE_6_ID = 6L;

    public static final int NURSE_6_APP_APPROVED_OR_PREDEF_BOOKED_COUNT = 4;

    public static final long ORDINATION_4_ID = 4L;

    public static final int NO_APP_ORDINATION_4 = 1;

    public static final int NO_APP_DOCTOR_4 = 2;



    @Autowired
    AppointmentService appointmentService;

    @Test
    public void getDoctorAppointments() {
        List<AppointmentCalendarDTO> appointments = appointmentService.getDoctorAppointments(DOCTOR_4_ID);
        assertThat(appointments).hasSize(DOCTOR_4_APP_APPROVED_OR_PREDEF_BOOKED_COUNT);
    }

    @Test
    public void getNurseAppointments() {
        List<AppointmentCalendarDTO> appointments = appointmentService.getNurseAppointments(NURSE_6_ID);
        assertThat(appointments).hasSize(NURSE_6_APP_APPROVED_OR_PREDEF_BOOKED_COUNT);
    }

    @Test
    public void getOrdinationAppointmentsDuringTheDay() {
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        List<Appointment> appointments = appointmentService.getOrdinationAppointmentsDuringTheDay(ORDINATION_4_ID, startDateTime);
        assertThat(appointments).hasSize(NO_APP_ORDINATION_4);

        for (Appointment appointment : appointments){
            assertEquals(ORDINATION_4_ID, appointment.getOrdination().getId());
        }
    }

    @Test
    public void getDoctorAppointmentsDuringTheDay() {
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        List<Appointment> appointments = appointmentService.getDoctorAppointmentsDuringTheDay(DOCTOR_4_ID, startDateTime);
        assertThat(appointments).hasSize(NO_APP_DOCTOR_4);
    }

    @Test
    public void getAppointment() {
    }

    @Test
    public void assignOrdination() {
    }

    @Test
    @Transactional
    public void testAssignOperationOrdination_Success() {

        Clinic clinic = new Clinic(NEW_CLINIC_NAME, NEW_CLINIC_DESCRIPTION, NEW_CLINIC_ADDRESS, NEW_CLINIC_CITY, 80, 60);
        clinic.setId(CLINIC_20_ID);
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        Patient patient = new Patient();
        patient.setId(1L);

        Nurse nurse = new Nurse();
        nurse.setClinic(clinic);
        nurse.setId(2L);
        nurse.setEmail("nurse@gmail.com");

        Ordination ordination = new Ordination();
        ordination.setNumber("120");
        ordination.setId(ORDINATION_20_ID);

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setClinic(clinic);

        Doctor doctor = new Doctor();
        doctor.setSpecialization(appointmentType);
        doctor.setClinic(clinic);
        doctor.setEmail("doctor@gmail.com");
        doctor.setId(20L);

        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setAppointmentEnum(AppointmentEnum.OPERATION);
        appointmentPrice.setAppointmentType(appointmentType);

        Appointment appointment = new Appointment();
        appointment.setClinic(clinic);
        appointment.setPrice(appointmentPrice);
        appointment.setStartDateTime(startDateTime);
        appointment.setEndDateTime(endDateTime);
        appointment.setStatus(AppointmentStatus.APPROVED);
        appointment.setId(APPOINTMENT_20_ID);
        appointment.setPatient(patient);
        patient.setEmail("patient@gmail.com");

        Set<Doctor> doctors = new HashSet<>();
        doctors.add(doctor);

        System.out.println(appointment.getId());
        System.out.println(ordination.getId());
        for (Doctor d : doctors){
            System.out.println(d.getId());
        }

        Appointment asssigned = appointmentService.assignOperationOrdination(appointment, ordination, doctors);

        assertNotNull(asssigned);
    }

    @Test
    public void cancelAppointment() {
    }

    @Test
    public void getHistoryApp() {
    }

    @Test
    public void getAvailableOrdinations() {
    }

    @Test
    public void createNew() {
    }

    @Test
    public void getFutureCancelAppointments() {
    }

    @Test
    public void getFutureFixAppointments() {
    }

    @Test
    public void cancelAppointmentP() {
    }
}