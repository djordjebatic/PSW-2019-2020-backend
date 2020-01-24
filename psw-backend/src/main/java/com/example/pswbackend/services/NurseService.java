package com.example.pswbackend.services;

import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.domain.PaidTimeOffNurse;
import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.dto.PaidTimeOffNurseDTO;
import com.example.pswbackend.dto.PrescriptionDTO;

public interface NurseService {

    Nurse getLoggedInNurse();
    PrescriptionDTO authenticatePrescription(Long nurseId, Long prescriptionId);
    PaidTimeOffNurse requestLeave(Long id, PaidTimeOffNurseDTO paidTimeOffNurseDTO);

    boolean alreadyRequestedLeave(Nurse nurse);
    boolean alreadyOnLeave(Nurse nurse);
}
