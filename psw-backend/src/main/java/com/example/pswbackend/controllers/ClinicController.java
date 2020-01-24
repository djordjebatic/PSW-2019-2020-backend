package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @GetMapping(value="/clinics", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<Clinic>> getAllClinics(){


        List<Clinic> lc= clinicService.findAll();

        if(lc==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(lc, HttpStatus.OK);
    }

    @GetMapping(value = "/clinic/{clinicId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT') or hasRole('CLINIC_ADMIN')")
    public ClinicDTO getClinic(@PathVariable long clinicId) {
        return this.clinicService.findById(clinicId);
    }


    @GetMapping(value = "/clinic-admin-clinic/{clinicAdminId}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Long> getClinicAdminClinic(@PathVariable long clinicAdminId) {

        return new ResponseEntity<Long>(clinicService.findByClinicAdminId(clinicAdminId), HttpStatus.OK);
    }
    


}
