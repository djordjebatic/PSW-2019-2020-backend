package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    MedicalRecord findById(long id);
    MedicalRecord findByPatientId(long id);
    List<MedicalRecord> findAll();

}


