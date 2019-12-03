package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value="/doctor/shedule-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> sheduleAppointment(AppointmentDoctorDTO dto){

        if (doctorService.sheduleAppointment(dto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // izmeniti tip statusa
        }
    }

    @GetMapping(value="/doctors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Doctor getDoctor(@PathVariable long id) {

        return doctorService.findById(id);
    }

    @GetMapping(value="/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Doctor> getDoctors() {

        return doctorService.findAll();
    }

}
