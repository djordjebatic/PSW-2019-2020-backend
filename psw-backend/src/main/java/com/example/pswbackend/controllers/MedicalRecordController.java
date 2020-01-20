package com.example.pswbackend.controllers;


import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.dto.MedicalRecordDTO;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        return medicalRecordService.finByPatientAndDoctorId(id, doctor.getId());
    }

    @GetMapping(value="/medicalRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalRecord> getMedicalRecords() {

        return medicalRecordService.findAll();
    }

    @PostMapping(value="/medicalRecords/save")
    public ResponseEntity<MedicalRecord> saveMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordService.save(medicalRecordDTO);
        if (medicalRecord == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
    }
}
