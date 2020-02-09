package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PredefinedAppointmentDTO;

import java.util.List;

public interface PredefinedAppointmentService{

    Appointment findById(Long id);
    List<PredefinedAppointmentDTO> findPredefinedByClinicId(long id);
    List<Appointment> findAll();
    PredefinedAppointmentDTO schedulePredefinedAppointment(Patient p, Appointment a);
}
