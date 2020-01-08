package com.example.pswbackend.services;

import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.dto.AppointmentDoctorDTO;

import java.util.List;

public interface DoctorService {

    boolean scheduleAppointment(AppointmentDoctorDTO dto);

    Doctor findById(long id);

    List<Doctor> findAll();

    Doctor getLoggedInDoctor();

}
