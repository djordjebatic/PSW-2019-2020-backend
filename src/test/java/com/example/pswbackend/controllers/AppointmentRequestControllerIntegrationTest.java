package com.example.pswbackend.controllers;

import com.example.pswbackend.constants.TestUtil;
import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
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

import static com.example.pswbackend.constants.AppointmentTypeConstants.*;
import static com.example.pswbackend.constants.PredefinedAppointmentConstants.APPOINTMENT_REQUEST_ID;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentRequestControllerIntegrationTest {

    public static final String URL_PREFIX = "/api/appointment-request";

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
    public void getById() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/"+APPOINTMENT_REQUEST_ID)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.doctorId").value(4));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void sendRequest() throws Exception{

        AppointmentRequestDTO dto = new AppointmentRequestDTO();
        dto.setPatientId(8L);
        dto.setDoctorsId(4L);
        dto.setClinicId(1L);
        dto.setStartTimeS("04.05.2020 18:50");
        dto.setAppointmentType(AppointmentEnum.EXAMINATION);

        /*String json = TestUtil.json(dto);
        mockMvc.perform(post(URL_PREFIX)
                .header("Authorization", token)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());*/

    }
}
