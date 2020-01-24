package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.FilterClinicsDTO;
import com.example.pswbackend.dto.ResultClinicDTO;
import com.example.pswbackend.repositories.ClinicRepository;
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

    @GetMapping(value = "/clinic/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('PATIENT')")
    public ResponseEntity<ClinicDTO> getClinic(@PathVariable long id) {
        return new ResponseEntity<>(clinicService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value="/filter-clinics/{date}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ResultClinicDTO>> filterClinics(@PathVariable String date, @PathVariable String type){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        FilterClinicsDTO dto = new FilterClinicsDTO(type, LocalDate.parse(date, formatter));

        if (clinicService.filterClinics(dto).isEmpty()){

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ResultClinicDTO> lc= clinicService.filterClinics(dto);
            return new ResponseEntity<>(lc, HttpStatus.OK);
        }
    }
    


}
