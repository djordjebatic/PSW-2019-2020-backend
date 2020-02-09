package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.AppointmentType;
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
public class AppointmentTypeRepositoryTest {

    public static final Long APPOINTMENT_TYPE_1_ID = 1L;
    public static final String APPOINTMENT_TYPE_NAME_1_ID = "Kardiologija";
    public static final int NUMBER_OF_ALL_APPOINTMENT_TYPES = 12;
    public static final Long CLINIC_1_ID= 1L;
    public static final int NUMBER_OF_APPOINTMENT_TYPES_FROM_CLINIC_1=4;
    public static final Long APPOINTMENT_TYPE_4_ID = 4L;
    public static final Boolean SUCCESS_OF_DELETION_OF_APPOINTMENT_TYPE_4_ID= true;
    public static final String APPOINTMENT_TYPE_NAME_OPSTA_PRAKSA = "Opsta praksa";
    public static final Long CLINIC_3_ID= 3L;
    public static final Long APPOINTMENT_TYPE_ID_WITH_APPOINTMENT_TYPE_NAME_OPSTA_PRAKSA_AND_CLINIC_3_ID=10L;


    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Test
    public void findOneById(){

        AppointmentType appointmentType = appointmentTypeRepository.findOneById(APPOINTMENT_TYPE_1_ID);
        assertEquals(APPOINTMENT_TYPE_1_ID, appointmentType.getId());
    }

    @Test
    public void findAll(){

        List<AppointmentType> appointmentTypes = appointmentTypeRepository.findAll();
        assertEquals(NUMBER_OF_ALL_APPOINTMENT_TYPES, appointmentTypes.size());
    }

    @Test
    public void findByClinicId(){

        List<AppointmentType> appointmentTypes = appointmentTypeRepository.findByClinicId(CLINIC_1_ID);
        assertEquals(NUMBER_OF_APPOINTMENT_TYPES_FROM_CLINIC_1, appointmentTypes.size());

        for (AppointmentType at : appointmentTypes){
            assertEquals(CLINIC_1_ID, at.getClinic().getId());
        }
    }

    @Test
    public void deleteOneById(){

        /*Boolean deleted = appointmentTypeRepository.deleteOneById(APPOINTMENT_TYPE_4_ID);
        assertEquals(SUCCESS_OF_DELETION_OF_APPOINTMENT_TYPE_4_ID, deleted);*/
    }

    @Test
    public void findByNameAndClinicId(){

        AppointmentType appointmentType = appointmentTypeRepository.findByNameAndClinicId(APPOINTMENT_TYPE_NAME_OPSTA_PRAKSA,CLINIC_3_ID);
        assertEquals(APPOINTMENT_TYPE_ID_WITH_APPOINTMENT_TYPE_NAME_OPSTA_PRAKSA_AND_CLINIC_3_ID, appointmentType.getId());
        assertEquals(APPOINTMENT_TYPE_NAME_OPSTA_PRAKSA, appointmentType.getName());
        assertEquals(CLINIC_3_ID, appointmentType.getClinic().getId());
    }

}
