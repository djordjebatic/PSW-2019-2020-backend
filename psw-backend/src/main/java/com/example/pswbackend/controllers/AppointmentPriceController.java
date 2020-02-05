package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AppointmentPrice;
import com.example.pswbackend.dto.AppointmentPriceDTO;
import com.example.pswbackend.dto.AppointmentPriceFullDTO;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.services.AppointmentPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AppointmentPriceController {

    @Autowired
    AppointmentPriceService appointmentPriceService;

    @Autowired
    AppointmentPriceRepository appointmentPriceRepository;

    @GetMapping(value = "/get-appointment-details/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<AppointmentPriceDTO> getAppointmentDetails(@PathVariable Long id){

        AppointmentPriceDTO dto = appointmentPriceService.getAppointmentPrice(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/get-appointment-prices")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AppointmentPriceFullDTO>> getAppointmentPrices(){

        List<AppointmentPriceFullDTO> list = appointmentPriceService.getAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
