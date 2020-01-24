package com.example.pswbackend.services;

import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.dto.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {

    MedicalRecord findById(long id);
    MedicalRecord finByPatientAndDoctorId(long id, long doctorId);
    MedicalRecord findByPatientId(long id);
    List<MedicalRecord> findAll();

    MedicalRecord editMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception;

}
