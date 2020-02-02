package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.NewOrdinationDTO;
import com.example.pswbackend.dto.OrdinationAssignDTO;

import java.util.List;
import java.util.Set;

public interface OrdinationService {

    Ordination findById(long id);
    List<Ordination> findByClinicId(Long clinicId);

    List<Ordination> findAll();
    Ordination addNew(NewOrdinationDTO dto);

    List<OrdinationAssignDTO> findAllOrdinationsInClinic(Clinic clinic);
    Appointment assignOrdinationForOperation(Long appointmentId, Long ordinationId, Set<Doctor> doctors);

    void assignOrdinationAutomatically();

    Boolean updateOrdination(Ordination ordination, NewOrdinationDTO dto);
    Boolean deleteOrd(Long id);
}
