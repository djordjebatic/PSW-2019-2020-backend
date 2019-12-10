package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Diagnosis;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.DiagnosisDTO;
import com.example.pswbackend.dto.RegisterApprovalDTO;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.repositories.DiagnosisRepository;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cc-admin")
public class CCAdminController {

    @Autowired
    private PatientService patientService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    ClinicAdminService clinicAdminService;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @GetMapping(value="/all-registration-requests")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<List<RegisterApprovalDTO>> getAllRegistrationRequests() {
        return new ResponseEntity<>(patientService.findByStatus(Status.AWAITING_APPROVAL), HttpStatus.OK);
    }

    @PutMapping(value="/approve-registration-request/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Patient> approveRegistrationRequest(@PathVariable Long id ){
        Patient patient = patientService.approveRegistration(id);

        if (patient == null){
            return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @PutMapping(value="/reject-registration-request/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Patient> rejectRegistrationRequest(@RequestBody String message, @PathVariable Long id ){

        boolean reject = patientService.rejectRegistration(id, message);

        if (reject){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/register-clinic")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Clinic> registerClinic(@RequestBody ClinicDTO clinicDTO){
        Clinic clinic = clinicService.register(clinicDTO);
        if (clinic == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @PostMapping(value="/register-clinic-admin")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<ClinicAdmin> registerClinic(@RequestBody ClinicAdminDTO clinicAdminDTO){
        ClinicAdmin newClinicAdmin = clinicAdminService.register(clinicAdminDTO);
        if (newClinicAdmin == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newClinicAdmin, HttpStatus.OK);
    }

    @GetMapping(value="/all-diagnosis")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis() {
        return new ResponseEntity<>(diagnosisRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/add-diagnosis")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO){
        Diagnosis diagnosis = new Diagnosis(diagnosisDTO.getName(), diagnosisDTO.getDescription());

        if (diagnosisRepository.findByName(diagnosis.getName()) == null) {
            diagnosisRepository.save(diagnosis);
            return new ResponseEntity<>(diagnosis, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-diagnosis/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody DiagnosisDTO diagnosisDTO){

        Diagnosis diagnosis = diagnosisRepository.findOneById(id);

        if (diagnosis == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        diagnosis.setName(diagnosisDTO.getName());
        diagnosis.setDescription(diagnosisDTO.getDescription());
        diagnosisRepository.save(diagnosis);

        return new ResponseEntity<>(diagnosis, HttpStatus.OK);

    }

    @PutMapping(value = "/delete-diagnosis/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> deleteDiagnosis(@PathVariable Long id){

        Diagnosis diagnosis = diagnosisRepository.findOneById(id);

        if (diagnosis == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        diagnosisRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
