package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping(value="/clinics/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClinicDTO getClinic(@PathVariable long id) {

        return clinicService.findById(id);
    }

    @GetMapping(value="/clinics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Clinic> getClinics() {

        return clinicService.findAll();
    }
}
