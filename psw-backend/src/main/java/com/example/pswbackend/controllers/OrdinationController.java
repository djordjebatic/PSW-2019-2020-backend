package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.dto.AssignOperationDTO;
import com.example.pswbackend.dto.NewOrdinationDTO;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.OrdinationRepository;
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
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    private OrdinationRepository ordinationRepository;

    // everyday at midnight
    @Scheduled(cron = "${appointment.cron}")
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

    @PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Ordination> addNew(@RequestBody NewOrdinationDTO dto) {

        return new ResponseEntity<Ordination>(ordinationService.addNew(dto), HttpStatus.CREATED);
    }

    @PutMapping(value="/delete/{ordId}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> deleteOrd(@PathVariable Long ordId) {

        Boolean deleted = ordinationService.deleteOrd(ordId);

        if (deleted) {
            return new ResponseEntity<Boolean>(ordinationService.deleteOrd(ordId), HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(ordinationService.deleteOrd(ordId), HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/{ordinationId}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> updateOrd(@PathVariable Long ordinationId, @RequestBody NewOrdinationDTO dto){

        Ordination o = ordinationService.findById(ordinationId);

        if (o == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean success = ordinationService.updateOrdination(o, dto);

        if(success) {
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/assign-operation-ordination", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity assignOrdinationForOperation(@Valid @RequestBody AssignOperationDTO assignOperationDTO){
        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();

        Appointment appointment = appointmentService.getAppointment(assignOperationDTO.getAppointmentId());

        if (!clinicAdmin.getClinic().getId().equals(appointmentService.getAppointment(assignOperationDTO.getAppointmentId()).getClinic().getId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (appointment.getStatus().equals(AppointmentStatus.APPROVED)){
            return new ResponseEntity<>("Ordination for this appointment has already been assigned!", HttpStatus.BAD_REQUEST);
        }

        Set<Doctor> doctors = new HashSet<>();
        for (Long id : assignOperationDTO.getDoctorIds()){
            doctors.add(doctorService.findById(id));
        }

        try {
            Appointment assignOrdinationForOperation = ordinationService.assignOrdinationForOperation(assignOperationDTO.getAppointmentId(), assignOperationDTO.getOrdinationId(), doctors);
            return new ResponseEntity<>(assignOrdinationForOperation, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (IllegalTransactionStateException e){
            return new ResponseEntity<>("Ordination has already been assigned by someone else while you were sending the request. Please try again.", HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/clinic-ordinations/{clinicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Ordination>> getClinicOrdinations(@PathVariable Long clinicId){

        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();
        if (!clinicAdmin.getClinic().getId().equals(clinicId)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<List<Ordination>>(ordinationService.findByClinicId(clinicId), HttpStatus.OK);
    }

}
