package com.example.pswbackend.services;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.OrdinationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class OrdinationServiceTest {

    public static final int YEAR = 2020;

    public static final Month MONTH_DATE = Month.JANUARY;

    public static final int DAY_OF_MONTH_START = 20;

    public static final int DAY_OF_MONTH_END = 29;

    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;

    public static final String NEW_CLINIC_NAME = "Clinic";

    public static final String NEW_CLINIC_DESCRIPTION = "Best Clinic Ever";

    public static final String NEW_CLINIC_ADDRESS = "Balzakova 111";

    public static final String NEW_CLINIC_CITY = "Novi Sad";

    public static final Long CLINIC_1_ID = 1L;

    public static final Long ORDINATION_1_ID = 1L;

    public static final Long APPOINTMENT_ID = 1L;

    @Autowired
    AppointmentService appointmentService;

    @MockBean
    private OrdinationRepository ordinationRepositoryMock;

    @MockBean
    private AppointmentService appointmentServiceMock;

    @Test
    public void findAllOrdinationsInClinic() {
    }

    @Test
    @Transactional
    public void assignOrdinationForOperation_Success() throws Exception{


    }

    @Test
    public void assignOrdinationAutomatically() {
    }
}