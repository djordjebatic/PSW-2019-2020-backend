package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PredefinedAppointmentService{

    Appointment findById(Long id);
    List<Appointment> findByClinicId(Long id);
    List<Appointment> findAll();
    Appointment schedulePredefinedAppointment(Patient patient, Appointment appointment);
}
