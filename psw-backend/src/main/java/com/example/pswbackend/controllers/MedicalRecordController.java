package com.example.pswbackend.controllers;


import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    DoctorService doctorService;

    @GetMapping(value="/medicalRecords/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public MedicalRecord getMedicalRecord(@PathVariable long id) {
        Doctor doctor = doctorService.getLoggedInDoctor();
        return medicalRecordService.findByPatientId(id, doctor.getId());
    }

    @GetMapping(value="/medicalRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalRecord> getMedicalRecords() {

        return medicalRecordService.findAll();
    }
}
