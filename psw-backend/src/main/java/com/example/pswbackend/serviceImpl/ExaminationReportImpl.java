package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.ExaminationReportDTO;
import com.example.pswbackend.repositories.DiagnosisRepository;
import com.example.pswbackend.repositories.DrugRepository;
import com.example.pswbackend.repositories.ExaminationReportRepository;
import com.example.pswbackend.services.ExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminationReportImpl implements ExaminationReportService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    ExaminationReportRepository examinationReportRepository;

    @Override
    public ExaminationReportDTO create(Appointment appointment, Doctor doctor, ExaminationReportDTO examinationReportDTO) {
        Diagnosis diagnosis = diagnosisRepository.findOneById(examinationReportDTO.getDiagnosisId());
        if (diagnosis == null){
            return null;
        }

        ExaminationReport examinationReport = new ExaminationReport(LocalDateTime.now(), examinationReportDTO.getComment(),
                appointment.getPatient().getMedicalRecord(), diagnosis, doctor, appointment);

        Set<Prescription> prescriptions = new HashSet<>();
        for (Long drugId : examinationReportDTO.getDrugIds()) {
            Drug drug = drugRepository.findOneById(drugId);
            if (drug == null) {
                return null;
            }
            Prescription prescription = new Prescription(drug, examinationReport, appointment.getNurse());
            prescriptions.add(prescription);
        }

        examinationReport.setPrescriptions(prescriptions);

        ExaminationReport created = examinationReportRepository.save(examinationReport);

        return new ExaminationReportDTO(created);
    }
}
