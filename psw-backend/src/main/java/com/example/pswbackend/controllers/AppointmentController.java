package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.repositories.OrdinationRepository;
import com.example.pswbackend.dto.AppointmentCalendarDTO;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.*;
import com.example.pswbackend.dto.AvailableAppointmentDTO;
import com.example.pswbackend.dto.NewAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping(value = "/get-awaiting-approval-appointments")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Appointment>> getAwaitingApprovalAppointments(){
        return new ResponseEntity<>(appointmentService.getAwaitingApprovalAppointments(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-awaiting-appointments")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Appointment>> getAwaitingAppointments(){
        return new ResponseEntity<>(appointmentService.getAwaitingAppointments(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-canceled-appointments")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Appointment>> getCanceledAppointments(){
        return new ResponseEntity<>(appointmentService.getCanceledAppointments(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-predefined-available-appointments")
    @PreAuthorize("hasRole('CLINIC_ADMIN') or hasRole('PATIENT')")
    public ResponseEntity<List<Appointment>> getPredefinedAwailableAppointments(){
        return new ResponseEntity<>(appointmentService.getPredefinedAvailableAppointments(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-predefined-booked-appointments")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Appointment>> getPredefinedBookedAppointments(){
        return new ResponseEntity<>(appointmentService.getPredefinedBookedAppointments(), HttpStatus.OK);
    }

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
        try {
            return new ResponseEntity<List<Ordination>>(appointmentService.getAvailableOrdinations(dto), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @PutMapping("/cancel/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<PredefinedAppointmentDTO> cancelAppointments(@PathVariable("id") Long appointmentId){
        Doctor doctor = doctorService.getLoggedInDoctor();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Appointment appointment = appointmentService.cancelAppointment(doctor, appointmentId);
        if (appointment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PredefinedAppointmentDTO dto= new PredefinedAppointmentDTO(appointment, Long.parseLong("1"));

        return new ResponseEntity<>(dto, HttpStatus.OK);
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

        Appointment a = appointmentService.createNew(dto);
        if (a == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(a, HttpStatus.CREATED);
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
    public ResponseEntity<Appointment> cancelAppointmentP(@PathVariable Long appointmentId){

        Appointment appointment = appointmentService.cancelAppointmentP(appointmentId);
        if (appointment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

}
