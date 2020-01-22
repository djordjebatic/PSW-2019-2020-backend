package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.dto.AppointmentCalendarDTO;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/get-predefined-awailable-appointments")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Appointment>> getPredefinedAwailableAppointments(){
        return new ResponseEntity<>(appointmentService.getPredefinedAwailableAppointments(), HttpStatus.OK);
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
    public ResponseEntity<Appointment> cancelAppointments(@PathVariable("id") Long appointmentId){
        Doctor doctor = doctorService.getLoggedInDoctor();
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Appointment appointment = appointmentService.cancelAppointment(doctor, appointmentId);
        if (appointment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
