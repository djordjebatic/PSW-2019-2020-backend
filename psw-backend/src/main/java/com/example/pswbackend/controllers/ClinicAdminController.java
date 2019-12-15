package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.dto.ChangePasswordDTO;
import com.example.pswbackend.dto.QuickReservationDTO;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/clinic-admin")
public class ClinicAdminController {

    @Autowired
    private ClinicAdminService clinicAdminService;

    @PostMapping(value="/quick-reservation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_CLINIC_ADMIN')")
    public ResponseEntity<Appointment> createQuickReservation(@RequestBody QuickReservationDTO dto){

        Appointment appointment = clinicAdminService.createQuickReservation(dto);

        if(appointment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // izmeniti tip statusa
        }

        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @PutMapping(value="/change-password", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> changePassword(ChangePasswordDTO dto){

        //TODO implement changing password (ClinicAdmin)

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
