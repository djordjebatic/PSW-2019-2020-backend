package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.repositories.PaidTimeOffDoctorRepository;
import com.example.pswbackend.repositories.PaidTimeOffNurseRepository;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/absence")
public class AbsenceRequestsController {

    @Autowired
    private PaidTimeOffDoctorRepository paidTimeOffDoctorRepository;

    @Autowired
    private PaidTimeOffNurseRepository paidTimeOffNurseRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @GetMapping(value="/doctor-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<PaidTimeOffDoctor>> getDoctorAbsenceRequests(){

        List<PaidTimeOffDoctor> list = paidTimeOffDoctorRepository.findAll();
        List<PaidTimeOffDoctor> listToReturn = new ArrayList<>();
        ClinicAdmin doc = clinicAdminService.getLoggedInClinicAdmin();

        if (list == null){
            return null;
        }

        if (doc == null){
            return null;
        }

        for (int i=0;i < list.size(); i++){
            if (list.get(i).getDoctor().getClinic().getId() == doc.getClinic().getId()){
                listToReturn.add(list.get(i));
            }
        }

        return new ResponseEntity<List<PaidTimeOffDoctor>>(listToReturn, HttpStatus.OK);
    }

    @GetMapping(value="/nurse-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<PaidTimeOffNurse>> getNurseAbsenceRequests(){

        List<PaidTimeOffNurse> list = paidTimeOffNurseRepository.findAll();
        List<PaidTimeOffNurse> listToReturn = new ArrayList<>();
        ClinicAdmin nur = clinicAdminService.getLoggedInClinicAdmin();

        if (list == null){
            return null;
        }

        if (nur == null){
            return null;
        }

        for (int i=0;i < list.size(); i++){
            if (list.get(i).getNurse().getClinic().getId() == nur.getClinic().getId()){
                listToReturn.add(list.get(i));
            }
        }

        return new ResponseEntity<List<PaidTimeOffNurse>>(listToReturn, HttpStatus.OK);
    }
}
