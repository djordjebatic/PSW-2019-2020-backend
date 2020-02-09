package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.ChangePasswordDTO;
import com.example.pswbackend.dto.NewDoctorDTO;
import com.example.pswbackend.dto.NewOrdinationDTO;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.services.AppointmentService;
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

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping(value="/doctor/schedule-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Account> scheduleAppointment(@RequestBody AppointmentDoctorDTO dto){

        if (doctorService.scheduleAppointment(dto)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // izmeniti tip statusa
        }
    }

    @PostMapping(value="/doctor/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Doctor> addNew(@RequestBody NewDoctorDTO dto) {

        return new ResponseEntity<Doctor>(doctorService.addNew(dto), HttpStatus.CREATED);
    }

    @GetMapping(value="/doctors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Doctor getDoctor(@PathVariable long id) {

        return doctorService.findById(id);
    }

    @GetMapping(value="/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Doctor> getDoctors() {

        return doctorService.findAll();
    }

    @PostMapping(value="/doctors-by-working-time", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewDoctorDTO> getDoctorsByWorkTime(@RequestBody DoctorWorkTimeDTO dto) {


        return doctorService.findByWorkingTime(dto.getStart(), dto.getEnd());
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
    public ResponseEntity<List<NewDoctorDTO>> getClinicDoctors(@PathVariable Long clinicId) {

        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();
        if (!clinicAdmin.getClinic().getId().equals(clinicId)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<List<NewDoctorDTO>>(doctorService.findClinicDoctors(clinicId), HttpStatus.OK);
    }

    @GetMapping(value="/clinic-doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<NewDoctorDTO>> getClinicDoctors() {

        ClinicAdmin clinicAdmin = clinicAdminService.getLoggedInClinicAdmin();

        if (clinicAdmin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Clinic c = clinicAdmin.getClinic();
        if ( c == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<NewDoctorDTO>>(doctorService.findClinicDoctors(c.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/doctor/request-leave")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity requestLeave(@RequestBody PaidTimeOffDoctorDTO dto){

        Doctor doc = doctorService.getLoggedInDoctor();

        if (doc == null){
            return new ResponseEntity("Forbidden", HttpStatus.FORBIDDEN);
        }

        if (doctorService.alreadyRequestedLeave(doc)){
            return new ResponseEntity("You already requested a leave", HttpStatus.BAD_REQUEST);
        }

        if (doctorService.alreadyOnLeave(doc)){
            return new ResponseEntity("You are currently on leave until " + doc.getPaidTimeOffDoctor().getEndDateTime() +". Please wait until your current leave " +
                    "time ends until you request a new one.", HttpStatus.BAD_REQUEST);
        }

        PaidTimeOffDoctor paidTimeOffDoctor = doctorService.requestLeave(doc.getId(), dto);
        if (paidTimeOffDoctor == null){
            return new ResponseEntity("Request could not be accepted because you have scheduled appointments during this time", HttpStatus.CONFLICT);
        }

        return new ResponseEntity(paidTimeOffDoctor, HttpStatus.OK);
    }

    @PutMapping(value="/doctor/delete/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id) {

        Boolean deleted = doctorService.deleteOneById(id);

        if (deleted) {
            return new ResponseEntity<Boolean>(doctorService.deleteOneById(id), HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(doctorService.deleteOneById(id), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/available-doctors-by-date-and-time") //GET, ali nema @ResponseBody
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<NewDoctorDTO>> getAvailableDoctorsByDateAndTime(@RequestBody DateAndTimeDTO dto){

        List<NewDoctorDTO> list = doctorService.getAvailableDoctorsByDateAndTime(dto);

        if (list == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping(value = "/doctors-by-specialization/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<Doctor>> getDocsBySpecialization(@PathVariable Long id){

        List<Doctor> list = doctorService.getDocsBySpecialization(id);

        if (list == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list,HttpStatus.OK);
    }


}
