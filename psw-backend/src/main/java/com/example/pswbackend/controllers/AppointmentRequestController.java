package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AppointmentRequest;
import com.example.pswbackend.dto.AppointmentRequestDTO;
import com.example.pswbackend.services.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/appointment-request")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService appointmentRequestService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<AppointmentRequestDTO> getById(@PathVariable Long id){

        AppointmentRequestDTO req = appointmentRequestService.getById(id);

        if (req == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(req,HttpStatus.OK);
    }
}