package com.example.pswbackend.services;

import com.example.pswbackend.dto.AppointmentDoctorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Override
    public boolean sheduleAppointment(AppointmentDoctorDTO dto) {

        String message = "-----------------------------------" +
                "Date: " + dto.getDate() +
                "\nTime: " + dto.getTime() +
                "-----------------------------------";

        clinicAdminService.receiveAppointmentRequest(dto);

        emailService.sendEmail("jelenadostic2@gmail.com", "Sheduling Appointment", message);

        return true; //za sad uvek true
    }
}
