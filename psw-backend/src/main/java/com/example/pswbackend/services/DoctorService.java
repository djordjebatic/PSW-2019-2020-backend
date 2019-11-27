package com.example.pswbackend.services;

import com.example.pswbackend.dto.DoctorAppointmentDto;

public interface DoctorService {

    boolean sheduleAppointment(DoctorAppointmentDto dto);

}
