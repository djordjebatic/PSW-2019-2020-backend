package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
