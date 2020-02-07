package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.UserStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.example.pswbackend.constants.AppointmentConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentRepositoryTest {

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.JANUARY;

    public static final int DAY_OF_MONTH_START = 20;

    public static final int DAY_OF_MONTH_END = 29;

    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;

    public static final Long ORDINATION_3_ID = 3L;

    public static final Long ORDINATION_2_ID = 2L;

    public static final Long APPOINTMENT_2_ID = 2L;

    public static final Long CLINIC_2_ID = 2L;

    public static final Long DOCTOR_4_ID = 4L;

    public static final Long NURSE_6_ID = 6L;

    public static final Long PATIENT_10_ID = 10L;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testFindByClinicId() {

        List<Appointment> appointments = appointmentRepository.findByClinicId(CLINIC_2_ID);
        assertEquals(1, appointments.size());
    }

    @Test
    public void testFindByDoctorsIdAndStatusIn() {

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(APPOINTMENT_STATUS_APPROVED);
        statuses.add(APPOINTMENT_STATUS_CANCELED);
        List<Appointment> appointments = appointmentRepository.findByDoctorsIdAndStatusIn(DOCTOR_4_ID, statuses);
        assertEquals(4, appointments.size());
    }

    @Test
    public void testFindByOrdinationIdAndStatusNotOrderByStartDateTime() {
        List<Appointment> appointments = appointmentRepository.findByOrdinationIdAndStatusNotOrderByStartDateTime
                (ORDINATION_2_ID, APPOINTMENT_STATUS_AWAITING_APPROVAL);
        assertEquals(0, appointments.size());
    }

    @Test
    public void testFindByNurseIdAndStatusIn() {
        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(APPOINTMENT_STATUS_PREDEF_BOOKED);
        statuses.add(APPOINTMENT_STATUS_CANCELED);
        List<Appointment> appointments = appointmentRepository.findByNurseIdAndStatusIn
                (NURSE_6_ID, statuses);
        assertEquals(2, appointments.size());
    }

    @Test
    public void testFindByNurseIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn() {

    }

    @Test
    public void testFindByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn() {
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_END, START_TIME_HOUR, MIN);

        System.out.println(startDateTime.getYear());
        System.out.println(startDateTime.getMonth());
        System.out.println(startDateTime.getDayOfMonth());
        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(APPOINTMENT_STATUS_APPROVED);

        List<Appointment> appointmentList1 = appointmentRepository.findByOrdinationId(ORDINATION_3_ID);
        for (Appointment appointment : appointmentList1){
            System.out.println(appointment.getStartDateTime());
        }

        List<Appointment> appointmentList = appointmentRepository.findByOrdinationIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn
                (ORDINATION_3_ID, startDateTime, endDateTime, statuses);

        assertEquals(1, appointmentList.size());

        for (Appointment appointment : appointmentList) {
            assertEquals(ORDINATION_3_ID, appointment.getOrdination().getId());
        }
    }

    @Test
    public void testFindByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn() {
        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_END, START_TIME_HOUR, MIN);
        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(APPOINTMENT_STATUS_APPROVED);
        List<Appointment> appointments1 = appointmentRepository.findByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn
                (DOCTOR_4_ID, startDateTime, endDateTime, statuses);
        assertEquals(2, appointments1.size());

        for (Appointment appointment : appointments1) {
            assertEquals(APPOINTMENT_STATUS_APPROVED, appointment.getStatus());
            assertTrue(endDateTime.isAfter(appointment.getEndDateTime()));
            assertTrue(startDateTime.isBefore(appointment.getStartDateTime()));
        }

        //with predefined appointments
        statuses.add(APPOINTMENT_STATUS_PREDEF_BOOKED);
        List<Appointment> appointments2 = appointmentRepository.findByDoctorsIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndStatusIn
                (DOCTOR_4_ID, startDateTime, endDateTime, statuses);
        assertEquals(3, appointments2.size());

        for (Appointment appointment : appointments2) {
            assertTrue(endDateTime.isAfter(appointment.getEndDateTime()));
            assertTrue(startDateTime.isBefore(appointment.getStartDateTime()));
        }
    }
}
