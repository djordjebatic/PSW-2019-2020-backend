package com.example.pswbackend.services;

import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.dto.PrescriptionDTO;

import java.util.List;

public interface PrescriptionService {

    List<PrescriptionDTO> getByNurseId(Long id);
    List<String> getPrescriptionsPatient(ExaminationReport e);
}
