package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.repositories.OrdinationRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private OrdinationRepository ordinationRepository;

    @GetMapping(value = "/get-doctor-appointments")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<AppointmentCalendarDTO>> getDoctorAppointments() {
        Doctor doctor = doctorService.getLoggedInDoctor();
        try {
            return new ResponseEntity<>(appointmentService.getDoctorAppointments(doctor.getId()), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-doctor-appointments/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AppointmentCalendarDTO>> getDoctorAppointmentsCA(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(appointmentService.getDoctorAppointments(id), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-ordination-appointments/{ordinationId}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AppointmentCalendarDTO>> getOrdinationAppointments(@PathVariable Long ordinationId) {
        try {
            return new ResponseEntity<>(appointmentService.getOrdinationAppointments(ordinationId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/available-ordinations-by-date") //zapravo time
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Ordination>> getAvailableOrdinations(@RequestBody AvailableAppointmentDTO dto) {
        //try {
            return new ResponseEntity<List<Ordination>>(appointmentService.getAvailableOrdinations(dto), HttpStatus.OK);
        //}
        //catch (Exception e){
        //    return new ResponseEntity<>(e, HttpStatus.valueOf(500));
        //}
    }

    @GetMapping(value = "/get-appointment/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<AppointmentCalendarDTO> getAppointment(@PathVariable Long id) {
        Doctor doctor = doctorService.getLoggedInDoctor();
        Appointment appointment = appointmentService.getAppointment(id);

        if (!appointment.getDoctors().contains(doctor)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(new AppointmentCalendarDTO(appointment), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get-appointment-clinic-admin/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity getAppointmentClinicAdmin(@PathVariable Long id) {
        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();
        Appointment appointment = appointmentService.getAppointment(id);

        /*if (!appointment.getClinic().getClinicAdmins().contains(clinicAdmin)){
            System.out.println("0");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/

        try {
            return new ResponseEntity<>(new AppointmentCalendarClinicAdminDTO(appointment), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Parsing error", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get-ordination/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity getOrdination(@PathVariable Long id) {

        return new ResponseEntity<>(ordinationRepository.findOneById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/get-specialized-doctors/{appointmentId}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity getSpecializedDoctors(@PathVariable Long appointmentId) {
        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();
        Appointment appointment = appointmentService.getAppointment(appointmentId);

        return new ResponseEntity(doctorService.findByClinicIdAndSpecializationId(appointment), HttpStatus.OK);
    }

    @GetMapping(value = "/get-nurse-appointments")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<AppointmentCalendarDTO>> getNurseAppointments() {
        Nurse nurse = nurseService.getLoggedInNurse();
        try {
            return new ResponseEntity<>(appointmentService.getNurseAppointments(nurse.getId()), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/history/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<AppointmentHistoryDTO>> getHistoryApp(@PathVariable String id) {

        try {
            return new ResponseEntity<>(appointmentService.getHistoryApp(Long.parseLong(id)), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Appointment> createNew(@RequestBody NewAppointmentDTO dto){

        try {

            Appointment a = appointmentService.createNew(dto);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
                return new ResponseEntity<>(a, HttpStatus.CREATED);

        } catch (Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/future-cancel-appointments/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<PredefinedAppointmentDTO>> getFutureCancelAppointments(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(appointmentService.getFutureCancelAppointments(id) , HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/future-fix-appointments/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<PredefinedAppointmentDTO>> getFutureFixAppointments(@PathVariable String id) {

        try {
            return new ResponseEntity<>(appointmentService.getFutureFixAppointments(Long.parseLong(id)) , HttpStatus.OK);
        }
        catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cancel-Patient/{appointmentId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity cancelAppointmentP(@PathVariable Long appointmentId){

        try {
            Appointment appointment = appointmentService.cancelAppointmentP(appointmentId);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        }
        catch (ValidationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
