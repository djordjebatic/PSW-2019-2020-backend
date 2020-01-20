package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.dto.OrdinationAssignDTO;

import java.util.List;
import java.util.Set;

public interface OrdinationService {

    Ordination findById(long id);

    List<Ordination> findAll();

    List<OrdinationAssignDTO> findAllOrdinationsInClinic(Clinic clinic);
    Appointment assignOrdinationForOperation(Long appointmentId, Long ordinationId, Set<Doctor> doctors);

    void assignOrdinationAutomatically();
}