package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PredefinedAppointmentService{

    Appointment findById(Long id);
    List<Appointment> findByClinicId(Long id);
    List<Appointment> findAll();
}
