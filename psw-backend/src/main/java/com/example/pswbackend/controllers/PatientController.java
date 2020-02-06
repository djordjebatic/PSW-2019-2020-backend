package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PatientDTO;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.NurseService;
import com.example.pswbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    NurseService nurseService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/patient/register")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO){

        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAddress(patientDTO.getAddress());
        patient.setCity(patientDTO.getCity());
        patient.setCountry(patientDTO.getCountry());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setEmail(patientDTO.getEmail());
        patient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        patient.setPatientStatus(Status.AWAITING_APPROVAL);
        patient.setMedicalNumber(patientDTO.getMedicalNumber());

        return new ResponseEntity<>(patientRepository.save(patient), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable long id) {

        return patientRepository.findById(id);
    }

    @GetMapping(value="/nurse-patients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('NURSE')")
    public List<Patient> getNursePatients() {

        Nurse nurse = nurseService.getLoggedInNurse();
        return appointmentRepository.findPatientsByClinicId(nurse.getClinic().getId());
    }

    @GetMapping(value="/doctor-patients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Patient> getDoctorPatients() {

        Doctor doctor = doctorService.getLoggedInDoctor();
        return appointmentRepository.findPatientsByClinicId(doctor.getClinic().getId());
    }

    @PutMapping(value="/patients/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public Patient updatePatient(@PathVariable long id, PatientDTO dto){

        Patient patient= patientRepository.findById(id);

        if(patient==null){
            return null;
        }

        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setAddress(dto.getAddress());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setCity(dto.getCity());
        patient.setCountry(dto.getCountry());
        patient.setPassword(dto.getPassword());

        return patient;
    }

}
