package com.example.pswbackend.services;

import com.example.pswbackend.domain.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {

    MedicalRecord findById(long id);
    MedicalRecord findByPatientId(long id, long doctorId);
    List<MedicalRecord> findAll();
}
