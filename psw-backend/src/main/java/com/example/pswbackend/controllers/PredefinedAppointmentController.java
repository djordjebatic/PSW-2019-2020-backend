package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.PredefinedAppointmentRepository;
import com.example.pswbackend.services.PredefinedAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PredefinedAppointmentController {

    @Autowired
    PredefinedAppointmentService predefinedAppointmentService;

    @PostMapping(value = "/schedule-predefined-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> schedulePredefinedAppointment(Patient patient, Appointment appointment) {

        Appointment appointment1 = predefinedAppointmentService.schedulePredefinedAppointment(patient, appointment);

        if(appointment1.getStatus()==AppointmentStatus.PREDEF_BOOKED){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}