package com.example.pswbackend.controllers;
import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.AppointmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.annotation.PostConstruct;
import static com.example.pswbackend.constants.AppointmentConstants.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentControllerUnitTest {

    public static final String URL_PREFIX = "/api/appointment";

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

    @Autowired
    AppointmentService appointmentServiceMock;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Before
    public void login(){
        ResponseEntity<AccountTokenState> responseEntity = testRestTemplate.postForEntity("/auth/login",
                new JwtAuthenticationRequest("cadmin1@gmail.com", ADMIN_PASSWORD), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();
    }

    @Test
    public void getAwaitingApprovalAppointments(){
    }

    @Test
    public void testGetAwaitingAppointments_Success() throws Exception{

        /*Clinic clinic = new Clinic(NEW_CLINIC_NAME, NEW_CLINIC_DESCRIPTION, NEW_CLINIC_ADDRESS, NEW_CLINIC_CITY);
        clinic.setId(CLINIC_ID);

        LocalDateTime startDate = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, START_TIME_HOUR, MIN, SEC);
        LocalDateTime endDate = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, END_TIME_HOUR, MIN, SEC);

        Appointment appointment1 = new Appointment(EXAMINATION, new DateTimeInterval(startDate, endDate),
                EXAMINATION_STATUS_AWAITING, null, null, DISCOUNT, null, clinic, null);
        Appointment appointment2 = new Appointment(EXAMINATION, new DateTimeInterval(startDate, endDate),
                EXAMINATION_STATUS_AWAITING, null, null, DISCOUNT, null, clinic, null);

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);

        Mockito.when(appointmentServiceMock
                .getAwaitingAppointments(eq(KIND_EXAMINATION), any(ClinicAdministrator.class), eq(page))).thenReturn(examinationPagingDTO);

        mockMvc.perform(get(URL_PREFIX + "/get-awaiting-appointments")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.examinationList").value(hasSize(LIST_EXAMINATIONS_COUNT)))
                .andExpect(jsonPath("$.examinationList.[*].status").value(hasItem(EXAMINATION_STATUS_AWAITING.toString())))
                .andExpect(jsonPath("$.numberOfItems").value(LIST_EXAMINATIONS_COUNT));

        verify(examinationServiceMock, times(1))
                .getAwaitingExaminations(eq(KIND_EXAMINATION), any(ClinicAdministrator.class), eq(page));*/
    }

    @Test
    public void getPredefinedAvailableAppointments() {
    }

    @Test
    public void getPredefinedBookedAppointments() {
    }

    @Test
    public void getDoctorAppointments() {
    }

    @Test
    public void getAppointment() {
    }

    @Test
    public void getNurseAppointments() {
    }

    @Test
    public void cancelAppointments() {
    }
}