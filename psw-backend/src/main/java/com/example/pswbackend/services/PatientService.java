package com.example.pswbackend.services;

import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PatientDTO;
import com.example.pswbackend.dto.RegisterApprovalDTO;
import com.example.pswbackend.enums.Status;

import java.util.List;

public interface PatientService {
    List<RegisterApprovalDTO> findByStatus (Status status);

    Patient approveRegistration(Long id);

    boolean rejectRegistration(Long id, String message);

    Patient registerPatient(PatientDTO patientDTO);
}
