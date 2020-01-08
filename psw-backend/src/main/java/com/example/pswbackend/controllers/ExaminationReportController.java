package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.ExaminationReportDTO;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.ExaminationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/examination-report")
public class ExaminationReportController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ExaminationReportService examinationReportService;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ExaminationReportDTO> create(@PathVariable("id") Long patientId, @Valid @RequestBody ExaminationReportDTO examinationReportDTO) {
        Doctor doctor = doctorService.getLoggedInDoctor();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Patient patient = patientRepository.findOneById(patientId);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LocalDateTime appointmentStartTime = LocalDateTime.now();
        Appointment ongoingExamination = appointmentService.getOngoingAppointment(patient.getId(), doctor.getId(), appointmentStartTime);
        if (ongoingExamination == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ExaminationReportDTO createdExaminationReportDTO = examinationReportService.create(ongoingExamination, doctor, examinationReportDTO);
        if (createdExaminationReportDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdExaminationReportDTO, HttpStatus.CREATED);
    }

}
