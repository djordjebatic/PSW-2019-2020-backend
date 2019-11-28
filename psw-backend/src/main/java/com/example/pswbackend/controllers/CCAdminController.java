package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.RegisterApprovalDTO;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value="/all-registration-requests")
    public ResponseEntity<List<RegisterApprovalDTO>> getAllRegistrationRequests() {
        return new ResponseEntity<>(patientService.findByStatus(Status.AWAITING_APPROVAL), HttpStatus.OK);
    }

    @PutMapping(value="/approve-registration-request/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> approveRegistrationRequest(@PathVariable Long id ){
        Patient patient = patientService.approveRegistration(id);

        if (patient == null){
            return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @PutMapping(value="/reject-registration-request/{id}")
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
    public ResponseEntity<Clinic> registerClinic(@RequestBody ClinicDTO clinicDTO){
        Clinic clinic = clinicService.register(clinicDTO);
        if (clinic == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @PostMapping(value="/register-clinic-admin")
    public ResponseEntity<ClinicAdmin> registerClinic(@RequestBody ClinicAdminDTO clinicAdminDTO){
        ClinicAdmin newClinicAdmin = clinicAdminService.register(clinicAdminDTO);
        if (newClinicAdmin == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newClinicAdmin, HttpStatus.OK);
    }
}
