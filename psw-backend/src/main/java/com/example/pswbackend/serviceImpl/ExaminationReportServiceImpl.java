package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.ExaminationReportDTO;
import com.example.pswbackend.enums.PrescriptionEnum;
import com.example.pswbackend.repositories.DiagnosisRepository;
import com.example.pswbackend.repositories.DrugRepository;
import com.example.pswbackend.repositories.ExaminationReportRepository;
import com.example.pswbackend.repositories.PrescriptionRepository;
import com.example.pswbackend.services.ExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ExaminationReportServiceImpl implements ExaminationReportService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    ExaminationReportRepository examinationReportRepository;

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Override
    @Transactional(readOnly = false)
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
            prescription.setPrescriptionEnum(PrescriptionEnum.ISSUED);
            prescriptions.add(prescription);
            prescriptionRepository.save(prescription);
        }

        examinationReport.setPrescriptions(prescriptions);

        return new ExaminationReportDTO(examinationReportRepository.save(examinationReport));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public ExaminationReportDTO edit(long examinationReportId, ExaminationReportDTO examinationReportDTO) {

        ExaminationReport examinationReport = examinationReportRepository.findOneById(examinationReportId);

        if (examinationReportDTO.getComment() == null){
            return null;
        }

        Diagnosis diagnosis = diagnosisRepository.findOneById(examinationReportDTO.getDiagnosisId());
        if (diagnosis == null){
            return null;
        }

        examinationReport.setDiagnosis(diagnosis);
        examinationReport.setComment(examinationReportDTO.getComment());
        examinationReport.setLastEdited(LocalDateTime.now());
        examinationReportRepository.save(examinationReport);
        return new ExaminationReportDTO(examinationReport);
    }

    @Override
    public ExaminationReport getExaminationReport(Long id) {
        return examinationReportRepository.findOneById(id);
    }
}
