package com.example.pswbackend.controllers;

import com.example.pswbackend.constants.TestUtil;
import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AssignOperationDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.OrdinationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.validation.ValidationException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author Djordje Batic
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrdinationControllerUnitTest {

    public static final String URL_PREFIX = "/api/ordination";

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.FEBRUARY;

    public static final int DAY_OF_MONTH_START = 27;


    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;

    public static final Long DOCTOR_4_ID = 4L;

    public static final long ORDINATION_4_ID = 4L;

    private String token;

    private AccountTokenState body;

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    AppointmentService appointmentService;

    @MockBean
    ClinicAdminService clinicAdminService;

    @MockBean
    DoctorService doctorService;

    @MockBean
    OrdinationService ordinationService;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Before
    public void login() {
        ResponseEntity<AccountTokenState> responseEntity = testRestTemplate.postForEntity("/auth/login",
                new JwtAuthenticationRequest("cadmin1@gmail.com", "admin"), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();
    }

    @Test
    public void testAssignOrdinationForOperation_Pass() throws Exception {

        ResponseEntity<AccountTokenState> responseEntity = testRestTemplate.postForEntity("/auth/login",
                new JwtAuthenticationRequest("cadmin1@gmail.com", "admin"), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();

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

        Appointment appointment1 = new Appointment(1L, appointmentPrice, ord, clinic, doctors, AppointmentStatus.AWAITING_APPROVAL, patient, nurse, startDateTime, endDateTime);

        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setId(17L);
        clinicAdmin.setClinic(clinic);

        given(this.clinicAdminService.getLoggedInClinicAdmin()).willReturn(clinicAdmin);
        given(this.appointmentService.getAppointment(1L)).willReturn(appointment1);
        given(this.doctorService.findById(4l)).willReturn(doctor);

        AssignOperationDTO assignOperationDTO = new AssignOperationDTO();
        assignOperationDTO.setId(1L);
        assignOperationDTO.setAppointmentId(1L);
        assignOperationDTO.setOrdinationId(ORDINATION_4_ID);
        List<Long>doctorIds = new ArrayList<>();
        doctorIds.add(DOCTOR_4_ID);
        assignOperationDTO.setDoctorIds(doctorIds);

        given(this.ordinationService.assignOrdinationForOperation(1L, ORDINATION_4_ID, doctors)).willReturn(appointment1);

        String jsonString = TestUtil.json(assignOperationDTO);

        mockMvc.perform(post(URL_PREFIX + "/assign-operation-ordination")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(1L))
        ;
    }
}