package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.dto.MedicalRecordDTO;
import com.example.pswbackend.repositories.MedicalRecordRepository;
import com.example.pswbackend.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord findById(long id){ return medicalRecordRepository.findById(id);}

    @Override
    public MedicalRecord finByPatientAndDoctorId(long id, long doctorId){

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
    public MedicalRecord findByPatientId(long id) {
        return medicalRecordRepository.findByPatientId(id);
    }

    @Override
    public List<MedicalRecord> findAll(){ return medicalRecordRepository.findAll();}

    @Override
    // Isolation level set in order to prevent lost updated
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
    public MedicalRecord editMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception{

        long patientId = medicalRecordDTO.getPatientId();
        Integer height = medicalRecordDTO.getHeight();
        Integer weight = medicalRecordDTO.getWeight();
        String bloodType = medicalRecordDTO.getBloodType();
        String allergies = medicalRecordDTO.getAllergies();

        if (patientId <= 0 || height == null || weight == null || bloodType == null || allergies == null){
            return null;
        }

        MedicalRecord medicalRecord = findByPatientId(patientId);

        medicalRecord.setHeight(height);
        medicalRecord.setWeight(weight);
        medicalRecord.setBloodType(bloodType);
        medicalRecord.setAllergies(allergies);

        return medicalRecordRepository.save(medicalRecord);
    }
}
