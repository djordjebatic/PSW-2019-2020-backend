package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PredefinedAppointmentDTO;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.PredefinedAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PredefinedAppointmentController {

    @Autowired
    PredefinedAppointmentService predefinedAppointmentService;

    @Autowired
    AppointmentRepository predefinedAppointmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping(value = "/schedule-predefined-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> schedulePredefinedAppointment(@PathVariable long patientId, @PathVariable long appId) {

        Patient patient = patientRepository.findOneById(patientId);
        Appointment app = predefinedAppointmentRepository.findOneById(appId);

        if(app.getStatus()==AppointmentStatus.PREDEF_BOOKED){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment1 = predefinedAppointmentService.schedulePredefinedAppointment(patient, app);

        return new ResponseEntity<>(appointment1, HttpStatus.OK);
    }
    

    @GetMapping(value = "/predefined-appointments/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<List<PredefinedAppointmentDTO>> getPredefinedAppointments(@PathVariable long id) {

        try {
            return new ResponseEntity<>(predefinedAppointmentService.findByClinicId(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}