package com.example.pswbackend.services;

import com.example.pswbackend.repositories.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoctorServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private DoctorRepository doctorRepo;

    @Test
    void findById_whenIdExists_ReturnsDoctorInstance(){


    }

    @Test
    void findById_whenIdDoesNotExist_ThrowsException(){ // koji tacno exception?


    }

    @Test
    void findAll_whenDatabaseContainsDoctors_ReturnsListOfDoctorInstances(){


    }

    @Test
    void findAll_whenDatabaseDoesNotContainDoctors_ReturnsEmptyList(){


    }

    @Test
    void scheduleAppointment_whenAppointmentDtoIsNull_ThrowsException(){ // koji tacno exception


    }

    @Test
    void scheduleAppointment_whenAppointmentDtoIsValid_ReturnsTrue(){


    }
}
