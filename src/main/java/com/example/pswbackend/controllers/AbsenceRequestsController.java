package com.example.pswbackend.controllers;

import com.example.pswbackend.ServiceImpl.AbsenceRequestServiceImpl;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.domain.PaidTimeOffNurse;
import com.example.pswbackend.dto.AbsenceDoctorDTO;
import com.example.pswbackend.dto.AbsenceNurseDTO;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.repositories.PaidTimeOffDoctorRepository;
import com.example.pswbackend.repositories.PaidTimeOffNurseRepository;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    private AbsenceRequestServiceImpl absenceRequestService;

    @GetMapping(value="/doctor-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AbsenceDoctorDTO>> getDoctorAbsenceRequests(){

        List<AbsenceDoctorDTO> req = absenceRequestService.getDoctorAbsenceRequests();

        if (req == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<AbsenceDoctorDTO>>(req, HttpStatus.OK);
    }

    @GetMapping(value="/nurse-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AbsenceNurseDTO>> getNurseAbsenceRequests(){

        List<AbsenceNurseDTO> req = absenceRequestService.getNurseAbsenceRequests();

        if (req == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<AbsenceNurseDTO>>(req, HttpStatus.OK);
    }

    @PostMapping(value="/doctor-accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean acceptDoctorRequest(@RequestBody AbsenceDoctorDTO dto){

        return absenceRequestService.acceptDoctorRequest(dto);

    }

    @PostMapping(value="/nurse-accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean acceptNurseRequest(@RequestBody AbsenceNurseDTO dto){

        return absenceRequestService.acceptNurseRequest(dto);
    }

    @PostMapping(value="/doctor-deny", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean denyDoctorRequest(@RequestBody AbsenceDoctorDTO dto){

        return absenceRequestService.denyDoctorRequest(dto);

    }

    @PostMapping(value="/nurse-deny", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean denyNurseRequest(@RequestBody AbsenceNurseDTO dto){

        return absenceRequestService.denyNurseRequest(dto);
    }
}
