package com.example.pswbackend.services;

import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordServiceImpl implements  MedicalRecordService{

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord findById(long id){ return medicalRecordRepository.findById(id);}

    @Override
    public MedicalRecord findByPatientId(long id){ return medicalRecordRepository.findByPatientId(id);}

    @Override
    public List<MedicalRecord> findAll(){ return medicalRecordRepository.findAll();}
}
