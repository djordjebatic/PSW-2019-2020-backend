package com.example.pswbackend.services;

import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private DoctorRepository doctorRepo;

    @Override
    public boolean scheduleAppointment(AppointmentDoctorDTO dto) {

        String message = "-----------------------------------" +
                "Date: " + dto.getDate() +
                "\nTime: " + dto.getTime() +
                "-----------------------------------";

        clinicAdminService.receiveAppointmentRequest(dto);

        //emailService.sendEmail("jelenadostic2@gmail.com", "Sheduling Appointment", message);

        return true; //za sad uvek true
    }

    @Override
    public Doctor findById(long id) {
        return doctorRepo.findById(id).get();
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }
}
