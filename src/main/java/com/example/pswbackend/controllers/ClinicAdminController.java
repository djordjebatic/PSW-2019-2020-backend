package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.dto.QuickReservationDTO;
import com.example.pswbackend.services.ClinicAdminService;
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
@RequestMapping(value="/api/clinic-admin")
public class ClinicAdminController {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @PostMapping(value="/quick-reservation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public ResponseEntity<Appointment> createQuickReservation(@RequestBody QuickReservationDTO dto){

        if (dto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = clinicAdminService.createQuickReservation(dto);

        if(appointment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // izmeniti tip statusa
        }

        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    /*@PutMapping(value="/change-password", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> changePassword(ChangePasswordDTO dto){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/

}
