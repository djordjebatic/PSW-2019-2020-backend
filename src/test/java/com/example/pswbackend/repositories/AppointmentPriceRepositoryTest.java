package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentPrice;
import com.example.pswbackend.enums.AppointmentEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentPriceRepositoryTest {

    public static final Long APPOINTMENT_TYPE_1_ID = 1L;
    public static final Long APPOINTMENT_TYPE_2_ID = 2L;
    public static final int NUMBER_OF_PRICES_IN_APPOINTMENT_TYPE_2 = 2;
    public static final AppointmentEnum APPOINTMENT_ENUM_OPERATION =AppointmentEnum.OPERATION;
    public static final int APPOINTMENT_PRICE_OF_APPOINTMENT_TYPE_1_AND_APPOINTMENT_ENUM_OPERATION=3000;

    @Autowired
    AppointmentPriceRepository appointmentPriceRepository;

    @Test
    public void findByAppointmentTypeId(){

        List<AppointmentPrice> appointmentPrices= appointmentPriceRepository.findByAppointmentTypeId(APPOINTMENT_TYPE_2_ID);
        assertEquals(NUMBER_OF_PRICES_IN_APPOINTMENT_TYPE_2, appointmentPrices.size());

        for (AppointmentPrice ap : appointmentPrices){
            assertEquals(APPOINTMENT_TYPE_2_ID, ap.getAppointmentType().getId());
        }
    }

    @Test
    public void findByAppointmentTypeIdAndAppointmentEnum(){

        AppointmentPrice appointmentPrice= appointmentPriceRepository.findByAppointmentTypeIdAndAppointmentEnum(APPOINTMENT_TYPE_1_ID,APPOINTMENT_ENUM_OPERATION);
        assertEquals(APPOINTMENT_PRICE_OF_APPOINTMENT_TYPE_1_AND_APPOINTMENT_ENUM_OPERATION, appointmentPrice.getPrice(),0);

    }

}



