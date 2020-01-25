package com.example.pswbackend.controllers;

import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import com.example.pswbackend.services.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class AppointmentTypeController {

    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    AppointmentTypeService appointmentTypeService;

    @GetMapping(value="/types", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<AppointmentTypeDTO>> getAllAppTypes(){

        List<AppointmentTypeDTO> appTypeAll= appointmentTypeService.findAll();

        if(appTypeAll==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appTypeAll, HttpStatus.OK);
    }

    @GetMapping(value="/types/{clinicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AppointmentTypeDTO>> getAllAppTypesFromClinic(@PathVariable Long clinicId){

        List<AppointmentTypeDTO> appTypeAll= appointmentTypeService.findByClinicId(clinicId);

        if(appTypeAll==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appTypeAll, HttpStatus.OK);
    }

}
