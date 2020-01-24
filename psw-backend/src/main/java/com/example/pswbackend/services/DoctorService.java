package com.example.pswbackend.services;

import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.NewDoctorDTO;

import java.util.List;

public interface DoctorService {

    boolean scheduleAppointment(AppointmentDoctorDTO dto);

    Doctor findById(long id);

    Doctor addNew(NewDoctorDTO dto);

    List<Doctor> findAll();

    Doctor getLoggedInDoctor();

    List<Doctor> findClinicDoctors(Long clinicId);

}
