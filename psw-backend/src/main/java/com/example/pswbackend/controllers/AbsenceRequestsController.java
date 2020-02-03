package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AbsenceDoctorDTO;
import com.example.pswbackend.dto.AbsenceNurseDTO;
import com.example.pswbackend.dto.PaidTimeOffDoctorDTO;
import com.example.pswbackend.dto.PaidTimeOffNurseDTO;
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

    @GetMapping(value="/doctor-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AbsenceDoctorDTO>> getDoctorAbsenceRequests(){

        List<PaidTimeOffDoctor> list = paidTimeOffDoctorRepository.findAll();
        List<AbsenceDoctorDTO> listToReturn = new ArrayList<>();
        ClinicAdmin doc = clinicAdminService.getLoggedInClinicAdmin();

        if (list == null){
            return null;
        }

        if (doc == null){
            return null;
        }

        for (int i=0;i < list.size(); i++){
            if (list.get(i).getDoctor().getClinic().getId() == doc.getClinic().getId()){
                if (list.get(i).getPaidTimeOffStatus() == PaidTimeOffStatus.REQUESTED){
                    listToReturn.add(mapDoctorToDTO(list.get(i)));
                }
            }
        }

        return new ResponseEntity<List<AbsenceDoctorDTO>>(listToReturn, HttpStatus.OK);
    }

    @GetMapping(value="/nurse-requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AbsenceNurseDTO>> getNurseAbsenceRequests(){

        List<PaidTimeOffNurse> list = paidTimeOffNurseRepository.findAll();
        List<AbsenceNurseDTO> listToReturn = new ArrayList<>();
        ClinicAdmin nur = clinicAdminService.getLoggedInClinicAdmin();

        if (list == null){
            return null;
        }

        if (nur == null){
            return null;
        }

        for (int i=0;i < list.size(); i++){
            if (list.get(i).getNurse().getClinic().getId() == nur.getClinic().getId()){
                if (list.get(i).getPaidTimeOffStatus() == PaidTimeOffStatus.REQUESTED){
                    listToReturn.add(mapNurseToDTO(list.get(i)));
                }
            }
        }

        return new ResponseEntity<List<AbsenceNurseDTO>>(listToReturn, HttpStatus.OK);
    }

    private AbsenceNurseDTO mapNurseToDTO(PaidTimeOffNurse nurse){

        AbsenceNurseDTO dto = new AbsenceNurseDTO();

        dto.setEmail(nurse.getNurse().getUsername());
        dto.setFirstName(nurse.getNurse().getFirstName());
        dto.setLastName(nurse.getNurse().getLastName());
        dto.setComment(nurse.getComment());
        dto.setStartDateTime(nurse.getStartDateTime());
        dto.setEndDateTime(nurse.getEndDateTime());
        dto.setPaidTimeOffType(nurse.getPaidTimeOffType());
        dto.setId(nurse.getId());

        return dto;
    }

    private AbsenceDoctorDTO mapDoctorToDTO(PaidTimeOffDoctor doc){

        AbsenceDoctorDTO dto = new AbsenceDoctorDTO();

        dto.setEmail(doc.getDoctor().getUsername());
        dto.setFirstName(doc.getDoctor().getFirstName());
        dto.setLastName(doc.getDoctor().getLastName());
        dto.setComment(doc.getComment());
        dto.setStartDateTime(doc.getStartDateTime());
        dto.setEndDateTime(doc.getEndDateTime());
        dto.setPaidTimeOffType(doc.getPaidTimeOffType());
        dto.setId(doc.getId());

        return dto;
    }

    @PostMapping(value="/doctor-accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean acceptDoctorRequest(@RequestBody AbsenceDoctorDTO dto){
        try {
            PaidTimeOffDoctor doc = paidTimeOffDoctorRepository.findById(dto.getId()).get();
            doc.setPaidTimeOffStatus(PaidTimeOffStatus.APPROVED);
            paidTimeOffDoctorRepository.save(doc);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been accepted." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime();
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @PostMapping(value="/nurse-accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean acceptNurseRequest(@RequestBody AbsenceNurseDTO dto){
        try {
            PaidTimeOffNurse nur = paidTimeOffNurseRepository.findById(dto.getId()).get();
            nur.setPaidTimeOffStatus(PaidTimeOffStatus.APPROVED);
            paidTimeOffNurseRepository.save(nur);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been accepted." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime();
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @PostMapping(value="/doctor-deny", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean denyDoctorRequest(@RequestBody AbsenceDoctorDTO dto){
        try {
            PaidTimeOffDoctor doc = paidTimeOffDoctorRepository.findById(dto.getId()).get();
            doc.setPaidTimeOffStatus(PaidTimeOffStatus.DENIED);
            paidTimeOffDoctorRepository.save(doc);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been rejected." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime()
                    + "\n\n Reason for rejection: \n '" + dto.getDenialComment() + "'";
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @PostMapping(value="/nurse-deny", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public boolean denyNurseRequest(@RequestBody AbsenceNurseDTO dto){
        try {
            PaidTimeOffNurse nur = paidTimeOffNurseRepository.findById(dto.getId()).get();
            nur.setPaidTimeOffStatus(PaidTimeOffStatus.DENIED);
            paidTimeOffNurseRepository.save(nur);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been rejected." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime()
                    + "\n\n Reason for rejection: \n '" + dto.getDenialComment() + "'";
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
