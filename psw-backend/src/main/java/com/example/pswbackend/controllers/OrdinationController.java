package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.AssignOperationDTO;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.OrdinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/ordination")
public class OrdinationController {

    @Autowired
    private OrdinationService ordinationService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    // everyday at 0:45 am
    @Scheduled(cron = "0 45 0 * * *")
    public void assignOrdinationAutomatically() {
        ordinationService.assignOrdinationAutomatically();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ordination getOrdination(@PathVariable long id) {
        return ordinationService.findById(id);
    }

    @GetMapping(value="/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ordination> getOrdinations() {

        return ordinationService.findAll();
    }

    @PostMapping(value = "/assign-operation-ordination", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Appointment> assignOrdinationForOperation(@Valid @RequestBody AssignOperationDTO assignOperationDTO){
        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();

        if (!clinicAdmin.getClinic().getId().equals(appointmentService.getAppointment(assignOperationDTO.getAppointmentId()).getClinic().getId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Set<Doctor> doctors = new HashSet<>();
        for (Long id : assignOperationDTO.getDoctorIds()){
            doctors.add(doctorService.findById(id));
        }

        Appointment appointment = ordinationService.assignOrdinationForOperation(assignOperationDTO.getAppointmentId(), assignOperationDTO.getOrdinationId(), doctors);

        if (appointment == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

}
