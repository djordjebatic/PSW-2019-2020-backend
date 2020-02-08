package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.EditExaminationReportDTO;
import com.example.pswbackend.dto.ExaminationReportDTO;
import com.example.pswbackend.dto.ExaminationReportForPatientDTO;
import com.example.pswbackend.enums.PrescriptionEnum;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.ExaminationReportService;
import com.example.pswbackend.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/examination-report")
public class ExaminationReportController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    NurseService nurseService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ExaminationReportService examinationReportService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(value = "/get-examination-report/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<EditExaminationReportDTO> getExaminationReport(@PathVariable("id") Long examinationReportId) {
        Doctor doctor = doctorService.getLoggedInDoctor();

        ExaminationReport examinationReport = examinationReportService.getExaminationReport(examinationReportId);
        if (!examinationReport.getDoctor().getId().equals(doctor.getId())){
            System.out.println(examinationReport.getDoctor().getUsername() + " " + doctor.getUsername());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new EditExaminationReportDTO(examinationReport), HttpStatus.OK);
    }

    @PostMapping(value = "/create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ExaminationReportDTO> create(@PathVariable("id") Long appointmendId, @Valid @RequestBody ExaminationReportDTO examinationReportDTO) {
        Doctor doctor = doctorService.getLoggedInDoctor();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = appointmentRepository.findOneById(appointmendId);

        Patient patient = patientRepository.findOneById(appointment.getPatient().getId());
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LocalDateTime appointmentStartTime = LocalDateTime.now();
        Appointment ongoingAppointment = appointmentService.getOngoingAppointment(patient.getId(), doctor.getId(), appointmentStartTime);
        if (ongoingAppointment == null) {
            System.out.println("Ovde greska");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ExaminationReportDTO createdExaminationReportDTO = examinationReportService.create(ongoingAppointment, doctor, examinationReportDTO);
        if (createdExaminationReportDTO == null) {
            System.out.println("Ipak je ovde greska");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdExaminationReportDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ExaminationReportDTO> edit(@PathVariable("id") Long examinationReportId, @Valid @RequestBody ExaminationReportDTO examinationReportDTO) {

        ExaminationReport examinationReport = examinationReportService.getExaminationReport(examinationReportId);

        Doctor doctor = doctorService.getLoggedInDoctor();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!examinationReport.getDoctor().getId().equals(doctor.getId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ExaminationReportDTO ret = examinationReportService.edit(examinationReportId, examinationReportDTO);
        if (ret == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/get-prescriptions/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<Prescription>> create(@PathVariable("id") Long patientId) {
        Nurse nurse = nurseService.getLoggedInNurse();
        if (nurse == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Patient patient = patientRepository.findOneById(patientId);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Set<ExaminationReport> examinationReports = patient.getMedicalRecord().getExaminationReports();

        List<Prescription> prescriptions = new ArrayList<>();

        for (ExaminationReport examinationReport : examinationReports){
            if (examinationReport.getAppointment().getNurse().getId() == nurse.getId()) {
                for (Prescription p : examinationReport.getPrescriptions()) {
                    if (p.getPrescriptionEnum().equals(PrescriptionEnum.ISSUED)) {
                        prescriptions.add(p);
                    }
                }
            }
        }

        if (prescriptions.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        }


    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<ExaminationReportForPatientDTO> getExaminationReportP(@PathVariable Long id)  {

        ExaminationReport e = examinationReportService.getExaminationReport(id);
        if(e==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String lastEdited= "/";
        String comment= e.getComment();
        String diagnosis= e.getDiagnosis().getName();

        if(e.getLastEdited()==null){
            lastEdited="/";
        }

        if(e.getDiagnosis().getName()==null){
            diagnosis="/";
        }

        if(e.getComment()==null){
            comment="/";
        }

        ExaminationReportForPatientDTO dto = new ExaminationReportForPatientDTO(comment, diagnosis, lastEdited);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
