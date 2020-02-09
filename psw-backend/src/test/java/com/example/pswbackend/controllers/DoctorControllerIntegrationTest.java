package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.AppointmentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DoctorControllerIntegrationTest {

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
    AppointmentService appointmentServiceMock;

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
                new JwtAuthenticationRequest("dok@gmail.com", "dok"), AccountTokenState.class);
        token = "Bearer " + responseEntity.getBody().getAccessToken();
        body = responseEntity.getBody();
    }

    @Test
    public void requestLeave() throws Exception {
        mockMvc.perform(post(URL_PREFIX + "/doctor/request-leave")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(1)))
        ;
    }

}