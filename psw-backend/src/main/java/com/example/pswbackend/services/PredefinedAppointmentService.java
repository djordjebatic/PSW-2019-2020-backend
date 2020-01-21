package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PredefinedAppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PredefinedAppointmentService{

    Appointment findById(Long id);
    List<PredefinedAppointmentDTO> findByClinicId(long id);
    List<Appointment> findAll();
    Appointment schedulePredefinedAppointment(Patient patient, Appointment appointment);
}
