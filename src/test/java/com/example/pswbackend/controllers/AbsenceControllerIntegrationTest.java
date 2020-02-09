package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.dto.AbsenceDoctorDTO;
import com.example.pswbackend.dto.AbsenceNurseDTO;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.List;

import static com.example.pswbackend.constants.AppointmentTypeConstants.CADMIN_PASSWORD;
import static com.example.pswbackend.constants.PredefinedAppointmentConstants.APPOINTMENT_REQUEST_ID;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AbsenceControllerIntegrationTest {

    public static final String URL_PREFIX = "/api/absence";

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
                new JwtAuthenticationRequest("cadmin1@gmail.com", CADMIN_PASSWORD), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();
    }

    @Test
    public void getDoctorAbsenceRequests() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/doctor-requests")
                .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    public void getNurseAbsenceRequests() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/nurse-requests")
                .header("Authorization", token))
                .andExpect(status().isOk());

    }

    @Test
    public void acceptDoctorRequest() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/doctor-accept")
                .header("Authorization", token))
                .andExpect(status().isOk()); //TODO izmeni
    }

    @Test
    public void acceptNurseRequest() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/nurse-accept")
                .header("Authorization", token))
                .andExpect(status().isOk()); //TODO izmeni
    }

    @Test
    public void denyDoctorRequest(){

    }

    @Test
    public void denyNurseRequest(){

    }

}
