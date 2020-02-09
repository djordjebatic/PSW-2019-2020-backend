package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.services.ExaminationReportService;
import com.example.pswbackend.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PrescriptionController {

    @Autowired
    ExaminationReportService examinationReportService;

    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping(value = "/prescriptions/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<String>> getPrescriptionsPatient(@PathVariable Long id) {

        ExaminationReport e = examinationReportService.getExaminationReport(id);

        List<String> prescriptions = prescriptionService.getPrescriptionsPatient(e);

        if (prescriptions.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        }

    }
}
