package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.dto.QuickReservationDTO;

import java.util.List;

public interface ClinicAdminService {

    ClinicAdmin register(ClinicAdminDTO clinicAdminDTO);

    boolean receiveAppointmentRequest(AppointmentDoctorDTO dto);

    Appointment createQuickReservation(QuickReservationDTO dto);

    ClinicAdmin getLoggedInClinicAdmin();

}
