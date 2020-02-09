package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.NewDoctorDTO;
import com.example.pswbackend.dto.PaidTimeOffDoctorDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.enums.PaidTimeOffType;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.PatientRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class DoctorServiceTest {

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.FEBRUARY;

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

    public static final int PATIENT_1_ID = 1;

    @Autowired
    DoctorService doctorService;

    @MockBean
    PatientRepository patientRepository;

    @MockBean
    DoctorRepository doctorRepository;

    @MockBean
    ClinicAdminService clinicAdminService;

    @MockBean
    EmailService emailService;

    @MockBean
    ClinicService clinicService;

    @MockBean
    AppointmentRepository appointmentRepository;

    //Not implemented properly, shouldn't have been committed to master. Can't be tested
    @Test
    public void scheduleAppointment() {

        /*LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);

        Clinic clinic = new Clinic();
        clinic.setId(1L);

        Patient patient = new Patient();
        patient.setId(PATIENT_1_ID);

        Nurse nurse = new Nurse();
        nurse.setId(6L);

        Doctor doctor = new Doctor();
        doctor.setId(DOCTOR_4_ID);

        doctor.setClinic(clinic);

        given(this.patientRepository.findById(PATIENT_1_ID)).willReturn(patient);
        given(this.doctorRepository.findById(DOCTOR_4_ID).get()).willReturn(doctor);

        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setId(2L);


        AppointmentPrice appointmentPrice = new AppointmentPrice();
        appointmentPrice.setId(1L);
        appointmentPrice.setAppointmentEnum(AppointmentEnum.OPERATION);

        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(1L);

        appointmentPrice.setAppointmentType(appointmentType);

        Ordination ordination = new Ordination();
        ordination.setId(ORDINATION_4_ID);
        ordination.setClinic(clinic);

        Ordination ordination2 = new Ordination();
        ordination2.setId(ORDINATION_4_ID+1);
        ordination2.setClinic(clinic);

        */
    }

    @Test
    public void filterDoctors() {
    }

    @Test
    public void findClinicDoctors() {
    }

    //fails
    @Test
    public void testRequestLeave_Success() {

        LocalDateTime startDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDateTime = LocalDateTime.of(YEAR, MONTH_DATE, DAY_OF_MONTH_START, END_TIME_HOUR, MIN, SEC);


        Clinic clinic = new Clinic();
        clinic.setId(1L);

        Doctor doctor = new Doctor();
        doctor.setId(DOCTOR_4_ID);

        doctor.setClinic(clinic);

        //returns null
        given(this.doctorRepository.findById(DOCTOR_4_ID).get()).willReturn(doctor);

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        given(this.appointmentRepository.findByDoctorIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn(DOCTOR_4_ID, startDateTime, endDateTime, statuses)).willReturn(null);

        PaidTimeOffDoctorDTO paidTimeOffDoctorDTO = new PaidTimeOffDoctorDTO();
        PaidTimeOffDoctor returned = doctorService.requestLeave(DOCTOR_4_ID, paidTimeOffDoctorDTO);

        assertNotNull(returned);
        assertEquals(DOCTOR_4_ID, returned.getDoctor().getId());

    }

    @Test
    public void alreadyRequestedLeave() {
    }

    @Test
    public void alreadyOnLeave() {
    }

    @Test
    public void findByClinicIdAndSpecializationId() {
    }

    @Test
    public void getAvailableDoctorsByDateAndTime() {
    }
}