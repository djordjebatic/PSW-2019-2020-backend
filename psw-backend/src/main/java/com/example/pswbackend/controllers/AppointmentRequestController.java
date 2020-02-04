package com.example.pswbackend.controllers;

import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.repositories.AppointmentRequestRepository;
import com.example.pswbackend.services.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class AppointmentRequestController {

    @Autowired
    AppointmentRequestRepository appointmentRequestRepository;

    @Autowired
    AppointmentRequestService appointmentRequestService;

    @PostMapping(value="/appointment-request", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Boolean> sendRequest(@RequestBody AppointmentRequestDTO dto) {

        return new ResponseEntity<>(appointmentRequestService.sendRequest(dto), HttpStatus.OK);
    }
}
