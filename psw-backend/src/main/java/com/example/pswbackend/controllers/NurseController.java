package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.domain.PaidTimeOffNurse;
import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.dto.ExaminationReportDTO;
import com.example.pswbackend.dto.PaidTimeOffNurseDTO;
import com.example.pswbackend.dto.PrescriptionDTO;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.repositories.PrescriptionRepository;
import com.example.pswbackend.services.NurseService;
import com.example.pswbackend.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/nurse")
public class NurseController {

    @Autowired
    NurseService nurseService;

    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping(value = "/get-awaiting-prescriptions")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptions() {
        Nurse nurse = nurseService.getLoggedInNurse();

        List<PrescriptionDTO> prescriptions = prescriptionService.getByNurseId(nurse.getId());
        if (prescriptions == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    @PostMapping(value = "/authenticate/{id}")
    @PreAuthorize("hasRole('NURSE')")
    public  ResponseEntity<PrescriptionDTO> authenticate(@PathVariable("id") Long prescriptionId) {
        Nurse nurse = nurseService.getLoggedInNurse();

        PrescriptionDTO prescriptionDTO = nurseService.authenticatePrescription(nurse.getId(), prescriptionId);
        if (prescriptionDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(prescriptionDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/request-leave")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity requestLeave(@RequestBody PaidTimeOffNurseDTO paidTimeOffNurseDTO){

        Nurse nurse = nurseService.getLoggedInNurse();

        if (nurse == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if (nurseService.alreadyRequestedLeave(nurse)){
            return new ResponseEntity("You already requested a leave", HttpStatus.BAD_REQUEST);
        }

        if (nurseService.alreadyOnLeave(nurse)){
            return new ResponseEntity("You are currently on leave until " + nurse.getPaidTimeOffNurse().getEndDateTime() +". Please wait until your current leave " +
                    "time ends until you request a new one.", HttpStatus.BAD_REQUEST);
        }

        PaidTimeOffNurse paidTimeOffNurse = nurseService.requestLeave(nurse.getId(), paidTimeOffNurseDTO);
        if (paidTimeOffNurse == null){
            return new ResponseEntity("Request could not be accepted because you have scheduled appointments during this time", HttpStatus.CONFLICT);
        }

        return new ResponseEntity(paidTimeOffNurse, HttpStatus.OK);
    }
}
