package com.example.pswbackend.services;

import com.example.pswbackend.dto.AppointmentDoctorDTO;

public interface DoctorService {

    boolean sheduleAppointment(AppointmentDoctorDTO dto);

}
