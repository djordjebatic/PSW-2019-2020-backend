package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.dto.MedicalRecordDTO;
import com.example.pswbackend.repositories.MedicalRecordRepository;
import com.example.pswbackend.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord findById(long id){ return medicalRecordRepository.findById(id);}

    @Override
    public MedicalRecord findByPatientId(long id, long doctorId){

        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(id);
        Set<ExaminationReport> examinationReportSet = medicalRecord.getExaminationReports();

        Set<ExaminationReport> doctorsReport = new HashSet<>();

        for (ExaminationReport examinationReport : examinationReportSet){
            if (examinationReport.getDoctor().getId().equals(doctorId)){
                doctorsReport.add(examinationReport);
            }
        }

        medicalRecord.setExaminationReports(doctorsReport);

        return medicalRecord;
    }

    @Override
    public List<MedicalRecord> findAll(){ return medicalRecordRepository.findAll();}
}
