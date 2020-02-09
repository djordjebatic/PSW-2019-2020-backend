package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.domain.PaidTimeOffNurse;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

import static com.example.pswbackend.constants.AppointmentTypeConstants.CADMIN_PASSWORD;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class AbsenceControllerUnitTest {

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

    @Mock
    PaidTimeOffDoctor paidTimeOffDoctorMock;

    @Mock
    PaidTimeOffNurse paidTimeOffNurseMock;

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
        
    }

    @Test
    public void getNurseAbsenceRequests() throws Exception{

    }

    @Test
    public void acceptDoctorRequest() throws Exception{

    }

    @Test
    public void acceptNurseRequest() throws Exception{

    }

    @Test
    public void denyDoctorRequest(){

    }

    @Test
    public void denyNurseRequest(){

    }
}
