package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ChangePasswordDTO;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.CustomAccountDetailsService;
import com.example.pswbackend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private CustomAccountDetailsService accountDetailsService;

    @PostMapping(value="/doctor/schedule-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Account> scheduleAppointment(@RequestBody AppointmentDoctorDTO dto){

        if (doctorService.scheduleAppointment(dto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // izmeniti tip statusa
        }
    }

    @GetMapping(value="/doctors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Doctor getDoctor(@PathVariable long id) {

        return doctorService.findById(id);
    }

    @GetMapping(value="/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Doctor> getDoctors() {

        return doctorService.findAll();
    }


    @GetMapping(value="/doctors-list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ResultDoctorDTO>> getClinicDoctorsP(@PathVariable long id) {

            List<Doctor> doctorList =doctorService.findByClinicId(id);

            if(doctorList==null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            List<ResultDoctorDTO> resultList= new ArrayList<>();
            List<LocalDateTime> ldt = new ArrayList<>();
            List<String> free= new ArrayList<>();
            for(Doctor d : doctorList) {
                int r= d.getStars()/d.getNum_votes();
                ResultDoctorDTO resultDTO = new ResultDoctorDTO(d.getId().toString(), d.getFirstName(), d.getLastName(), Integer.toString(r), ldt, free);
                resultList.add(resultDTO);
            }

            return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @RequestMapping(value = "/doctor/change-password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Account> changePassword(@RequestBody AuthenticationController.PasswordChanger passwordChanger) {
        accountDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }

    @GetMapping(value="/filter-doctors/{date}/{type}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<ResultDoctorDTO>> filterDoctors(@PathVariable String date, @PathVariable String type, @PathVariable String id) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        FilterDoctorsDTO dto = new FilterDoctorsDTO(LocalDate.parse(date, formatter), type, id);


        if (doctorService.filterDoctors(dto).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<ResultDoctorDTO> lc = doctorService.filterDoctors(dto);
            return new ResponseEntity<>(lc, HttpStatus.OK);
        }
    }

    @GetMapping(value="/clinic-doctors/{clinicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Doctor>> getClinicDoctors(@PathVariable Long clinicId) {

        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();
        if (!clinicAdmin.getClinic().getId().equals(clinicId)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<List<Doctor>>(doctorService.findClinicDoctors(clinicId), HttpStatus.OK);
    }

}
