package com.example.pswbackend.controllers;

import com.example.pswbackend.constants.TestUtil;
import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.AppointmentTypeService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;;
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
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.io.IOException;

import static com.example.pswbackend.constants.AppointmentTypeConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AppointmentTypeControllerIntegrationTest {

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
    AppointmentTypeService appointmentTypeServiceMock;

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
    public void getAllAppTypes() throws Exception{

        ResponseEntity<AccountTokenState> responseEntity = testRestTemplate.postForEntity("/auth/login",
                new JwtAuthenticationRequest("patijent@gmail.com", PATIENT_PASSWORD), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();

        mockMvc.perform(get(URL_PREFIX + "/types")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    public void getAllAppTypesFromClinic() throws Exception{
        mockMvc.perform(get(URL_PREFIX + "/types/"+CLINIC_ID)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(NUMBER_OF_CLINICS_APPOINTMENT_TYPES)));
    }

    @Test
    public void getById() throws Exception{

        mockMvc.perform(get(URL_PREFIX + "/type/"+APPOINTMENT_TYPE_ID)
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(APPOINTMENT_TYPE_ID.intValue()))
                .andExpect(jsonPath("$.name").value(APPOINTMENT_NAME));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addNew() throws Exception{

                AppointmentTypeDTO appointmentTypeDTO = new AppointmentTypeDTO();
                appointmentTypeDTO.setName("Humana genetika");
                appointmentTypeDTO.setId(65L);
                appointmentTypeDTO.setClinicId(3L);

                String json = TestUtil.json(appointmentTypeDTO);
                mockMvc.perform(post(URL_PREFIX+ "/types/new")
                        .header("Authorization", token)
                        .contentType(contentType)
                        .content(json))
                        .andExpect(status().isCreated());
    }

    @Test
    public void updateAppType() throws Exception{

        AppointmentTypeDTO appointmentTypeDTO = new AppointmentTypeDTO();
        appointmentTypeDTO.setName("Humana genetika");
        appointmentTypeDTO.setId(65L);
        appointmentTypeDTO.setClinicId(3L);
        Long a=1L;

        String json = TestUtil.json(appointmentTypeDTO);
        this.mockMvc.perform(put(URL_PREFIX+"/type/"+a)
                .header("Authorization", token)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    //@Transactional
    //@Rollback(true)
    public void deleteOrd() throws Exception {

        /*mockMvc.perform(put(URL_PREFIX+ "/app-type/delete/"+1)
                .header("Authorization", token))
                .andExpect(status().isOk());*/
    }
}
