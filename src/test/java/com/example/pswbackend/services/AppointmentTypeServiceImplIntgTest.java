package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentTypeServiceImplIntgTest {

    @Autowired
    AppointmentTypeService appointmentTypeService;

    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Test
    public void findAll_returnsListOfAppointmentTypes(){

        assertEquals(7, appointmentTypeService.findAll().size());
    }

    @Test
    public void findByName_whenNameIsEmptyString_returnsNull(){

        assertNull(appointmentTypeService.findByName(""));
    }

    @Test
    public void findByName_whenNameIsValid_returnsNull(){

        AppointmentTypeDTO returned = appointmentTypeService.findByName("Gastrologija");
        assertEquals("Gastrologija",returned.getName());
    }

    @Test
    public void findByClinicId_whenClinicExists_returnsListOfAppointmentTypeDTOs(){

        List<AppointmentTypeDTO> list = appointmentTypeService.findByClinicId(1L);

        for(AppointmentTypeDTO dto:list){
            assertTrue(dto instanceof AppointmentTypeDTO);
        }

        assertEquals(4,list.size());
    }

    @Test
    public void findByClinicId_withNonExistingClinic_returnsListOfAppointmentTypeDTOs(){

        List<AppointmentTypeDTO> list = appointmentTypeService.findByClinicId(111L);

        assertEquals(0,list.size());
    }

}
