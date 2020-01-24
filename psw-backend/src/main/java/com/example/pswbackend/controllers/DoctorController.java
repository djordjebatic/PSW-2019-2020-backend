package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ChangePasswordDTO;
import com.example.pswbackend.dto.NewDoctorDTO;
import com.example.pswbackend.dto.NewOrdinationDTO;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.CustomAccountDetailsService;
import com.example.pswbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private CustomAccountDetailsService accountDetailsService;

    @PostMapping(value="/doctor/schedule-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Account> scheduleAppointment(@RequestBody AppointmentDoctorDTO dto){

        if (doctorService.scheduleAppointment(dto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // izmeniti tip statusa
        }
    }

    @PostMapping(value="/doctor/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Doctor> addNew(@RequestBody NewDoctorDTO dto) {

        return new ResponseEntity<Doctor>(doctorService.addNew(dto), HttpStatus.CREATED);
    }

    @GetMapping(value="/doctors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Doctor getDoctor(@PathVariable long id) {

        return doctorService.findById(id);
    }

    @GetMapping(value="/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Doctor> getDoctors() {

        return doctorService.findAll();
    }

    @GetMapping(value="/clinic-doctors/{clinicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Doctor>> getClinicDoctors(@PathVariable Long clinicId) {

        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();
        if (!clinicAdmin.getClinic().getId().equals(clinicId)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<List<Doctor>>(doctorService.findClinicDoctors(clinicId), HttpStatus.OK);
    }

}
