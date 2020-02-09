package com.example.pswbackend.services;

import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class AppointmentTypeServiceImplUnitTest {

    @Autowired
    AppointmentTypeService appointmentTypeService;

    @Mock
    AppointmentTypeRepository appointmentTypeRepository;

    @Mock
    private AppointmentType appointmentType;

    @Mock
    private List<AppointmentType> appointmentTypeList;

    @Test
    public void findAll_whenThereAreAppointmentTypes_returnsListOfAppointmentTypes(){

        appointmentTypeList.add(appointmentType);

        when(appointmentTypeRepository.findAll()).thenReturn(appointmentTypeList);

        assertEquals(1, appointmentTypeService.findAll().size());

    }

    @Test
    public void findAll_whenThereAreNoAppointmentTypes_returnsEmptyList(){

        List<AppointmentType> list = new ArrayList<>();

        when(appointmentTypeRepository.findAll()).thenReturn(list);

        assertEquals(0, appointmentTypeService.findAll().size());

    }

    @Test
    public void findByName_whenNameIsEmptyString_returnsNull(){

        when(appointmentTypeRepository.findByName("")).thenReturn(null);

        assertNull(appointmentTypeService.findByName(""));
    }

    @Test
    public void findByName_withNonExistingName_returnsNull(){

        when(appointmentTypeRepository.findByName("Nepostojeci")).thenReturn(null);

        assertNull(appointmentTypeService.findByName(""));
    }

    @Test
    public void findByName_whenNameIsValid_returnsNull(){

        appointmentType.setName("Gastrologija");
        when(appointmentTypeRepository.findByName("Gastrologija")).thenReturn(appointmentType);

        AppointmentTypeDTO returned = appointmentTypeService.findByName("Gastrologija");
        assertEquals(returned.getName(), "Gastrologija");
    }

}
