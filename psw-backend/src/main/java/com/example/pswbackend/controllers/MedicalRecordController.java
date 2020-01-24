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

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/medicalRecords")
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    DoctorService doctorService;

    @GetMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('NURSE', 'DOCTOR')")
    public MedicalRecord getMedicalRecord(@PathVariable long id) {
        Doctor doctor = doctorService.getLoggedInDoctor();
        return medicalRecordService.finByPatientAndDoctorId(id, doctor.getId());
    }

    @GetMapping(value="/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('NURSE', 'DOCTOR')")
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.findAll();
    }

    @GetMapping(value="/check-version", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('NURSE', 'DOCTOR')")
    public ResponseEntity getVersion(@PathParam("id") Long id, @PathParam("version") Long version) {

        if (medicalRecordService.findById(id).getVersion() > version){
            return new ResponseEntity("Medical information has been changed by some other user of the system while you were inputing data. Please refresh the page before continuing.", HttpStatus.CONFLICT);
        }
        else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping(value="/edit")
    @PreAuthorize("hasAnyRole('NURSE', 'DOCTOR')")
    public ResponseEntity editMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        try {
            MedicalRecord medicalRecord = medicalRecordService.editMedicalRecord(medicalRecordDTO);

            if (medicalRecord == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
}
