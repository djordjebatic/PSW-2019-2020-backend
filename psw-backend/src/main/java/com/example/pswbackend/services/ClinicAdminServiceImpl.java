package com.example.pswbackend.services;

import com.example.pswbackend.dto.AppointmentDoctorDTO;
import org.springframework.stereotype.Service;

@Service
public class ClinicAdminServiceImpl implements ClinicAdminService {

    @Override
    public boolean receiveAppointmentRequest(AppointmentDoctorDTO dto) {

        // proverava da li moze da zakaze

        return false;
    }
}
