package com.example.pswbackend.services;

import com.example.pswbackend.dto.AppointmentDoctorDTO;

public interface ClinicAdminService {

    boolean receiveAppointmentRequest(AppointmentDoctorDTO dto);

}
