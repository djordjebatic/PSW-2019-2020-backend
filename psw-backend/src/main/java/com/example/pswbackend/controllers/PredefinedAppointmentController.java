package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentRepository;
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

    @PostMapping(value = "/schedule-predefined-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> schedulePredefinedAppointment(Patient patient, Appointment appointment) {

        Appointment appointment1 = predefinedAppointmentService.schedulePredefinedAppointment(patient, appointment);

        if(appointment1.getStatus()==AppointmentStatus.PREDEF_BOOKED){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping(value="/predefined-appointments")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<Appointment>> getPredefinedAppointments() {

        //samo od te klinike

        return new ResponseEntity<>(predefinedAppointmentRepository.findAll(), HttpStatus.OK);
    }
}