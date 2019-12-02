package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.OrdinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class OrdinationController {

    @Autowired
    private OrdinationService ordinationService;

    @GetMapping(value="/ordinations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ordination getOrdination(@PathVariable long id) {

        return ordinationService.findById(id);
    }

    @GetMapping(value="/ordinations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ordination> getOrdinations() {

        return ordinationService.findAll();
    }

}
