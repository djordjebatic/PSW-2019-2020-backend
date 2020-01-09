package com.example.pswbackend.services;

import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.dto.PrescriptionDTO;

public interface NurseService {

    Nurse getLoggedInNurse();
    PrescriptionDTO authenticatePrescription(Long nurseId, Long prescriptionId);
}
