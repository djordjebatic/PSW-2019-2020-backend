package com.example.pswbackend.controllers;

import com.example.pswbackend.constants.TestUtil;
import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.dto.PAScheduleDTO;
import com.example.pswbackend.dto.PredefinedAppointmentDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.PredefinedAppointmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.List;

import static com.example.pswbackend.constants.AppointmentTypeConstants.*;
import static com.example.pswbackend.constants.AppointmentTypeConstants.APPOINTMENT_NAME;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PredefinedAppointmentControllerIntegrationTest {

    public static final String URL_PREFIX = "/api";

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
    PredefinedAppointmentService predefinedAppointmentService;

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
                new JwtAuthenticationRequest("patijent@gmail.com", PATIENT_PASSWORD), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void schedulePredefinedAppointment() throws Exception {

            PAScheduleDTO dto = new PAScheduleDTO();
            dto.setAppointmentId(6L);
            dto.setPatientId(9L);

            String json = TestUtil.json(dto);
            this.mockMvc.perform(post(URL_PREFIX+"/schedule-predefined-appointment")
                    .header("Authorization", token)
                    .contentType(contentType)
                    .content(json))
                    .andExpect(status().isOk());
    }

    @Test
    public void getPredefinedAppointments() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/predefined-appointments/"+1)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)));
                //napomena


    }
}