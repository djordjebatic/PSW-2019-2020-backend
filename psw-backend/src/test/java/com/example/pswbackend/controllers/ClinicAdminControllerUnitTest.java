package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.dto.QuickReservationDTO;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.ClinicAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static com.example.pswbackend.constants.AppointmentConstants.ADMIN_PASSWORD;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ClinicAdminControllerUnitTest {

    private MockMvc mockMvc;

    @Autowired
    ClinicAdminService clinicAdminServiceMock;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void login(){
        ResponseEntity<AccountTokenState> responseEntity = testRestTemplate.postForEntity("/auth/login",
                new JwtAuthenticationRequest("cadmin1@gmail.com", ADMIN_PASSWORD), AccountTokenState.class);
        String token = "Bearer " + responseEntity.getBody().getAccessToken();
        AccountTokenState body = responseEntity.getBody();
    }

    @Test
    public void createQuickReservation_withValidQuickReservationDTO_returnsResponseEntityAppointment() throws Exception {

        QuickReservationDTO dto = new QuickReservationDTO("2","4","1","2020-12-02 12:00","2020-12-02 13:00",1L,"0",5);
        ObjectMapper mapper = new ObjectMapper();
        String jsonDto = mapper.writeValueAsString(dto);
        String url = "/quick-reservation";

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonDto)).

                andExpect(status().isOk()).
                andExpect(content().contentType(contentType)).
                andExpect(jsonPath("$", hasSize(1)));

        //TODO dodati telo Appointment-a
    }

    @Test
    public void createQuickReservation_whenQuickReservationDTOIsNotValid_returnsResponseEntityNotFound() throws Exception {

        QuickReservationDTO dto = new QuickReservationDTO("7","4","1","2020-12-02 12:00","2020-12-02 13:00",1L,"0",5);
        ObjectMapper mapper = new ObjectMapper();
        String jsonDto = mapper.writeValueAsString(dto);
        String url = "/quick-reservation";

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonDto))
                .andExpect(status().isNotFound());

    }

    @Test
    public void createQuickReservation_whenNullIsPassed_returnsResponseEntityBadRequest() throws Exception{

        QuickReservationDTO dto = null;
        String url = "/quick-reservation";

        mockMvc.perform(get(url).content(dto.toString()))
                .andExpect(status().isBadRequest()); //TODO kako null vrednosti?
    }

}
