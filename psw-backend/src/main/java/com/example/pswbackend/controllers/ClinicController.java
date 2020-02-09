package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClinicController {

    @Autowired
    ClinicService clinicService;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    ClinicAdminService clinicAdminService;

    @Autowired
    AppointmentRequestService appointmentRequestService;


    @GetMapping(value="/clinics", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ResultClinicDTO>> getAllClinics(){

        List<Clinic> lc= clinicService.findAll();

        if(lc==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ResultClinicDTO> resultList= new ArrayList<>();
        for(Clinic c : lc) {
            ResultClinicDTO resultDTO = new ResultClinicDTO(c.getId().toString(), c.getName(), c.getDescription(), c.getAddress(), c.getCity(), Integer.toString(c.getStars()), Integer.toString(c.getNum_votes()), "");
            resultList.add(resultDTO);
        }

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @GetMapping(value="/filter-clinics/{date}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ResultClinicDTO>> filterClinics(@PathVariable String date, @PathVariable String type) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        FilterClinicsDTO dto = new FilterClinicsDTO(type, LocalDate.parse(date, formatter));

        if (clinicService.filterClinics(dto).isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ResultClinicDTO> lc = clinicService.filterClinics(dto);
            return new ResponseEntity<>(lc, HttpStatus.OK);
        }
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
    
    @PutMapping(value="/clinic/{clinicId}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> updateClinic(@PathVariable Long clinicId, @RequestBody ClinicDTO dto){

        Clinic c = clinicRepository.findOneById(clinicId);

        if (c == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean success = clinicService.updateClinic(c, dto);

        if(success) {
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/clinic-app-requests-ca")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AppointmentRequestDTO>> getAppointmentRequestsCa(){

        List<AppointmentRequestDTO> list = appointmentRequestService.getClinicAppReqCA();

        if (list == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping(value="/clinic-app-requests-doc")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<AppointmentRequestDTO>> getAppointmentRequestsDoc() {

        List<AppointmentRequestDTO> list = appointmentRequestService.getClinicAppReqDoc();

        if (list == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @GetMapping(value="/clinic-income")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Double> getIncome(){

        ClinicAdmin ca = clinicAdminService.getLoggedInClinicAdmin();

        if (ca == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.PREDEF_BOOKED);
        statuses.add(AppointmentStatus.APPROVED);
        Double income = clinicRepository.getIncomeOfTheClinic(ca.getClinic().getId());

        if (income == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Double>(income, HttpStatus.OK);

    }
}
