package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.CCAdminRepository;
import com.example.pswbackend.services.CCAdminService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/cc-admin")
public class CCAdminController {

    @Autowired
    private PatientService patientService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    ClinicAdminService clinicAdminService;

    @Autowired
    CCAdminService ccAdminService;

    @Autowired
    CCAdminRepository ccAdminRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping(value="/all-registration-requests")
    public ResponseEntity<List<RegisterApprovalDTO>> getAllRegistrationRequests() {
        return new ResponseEntity<>(patientService.findByStatus(Status.AWAITING_APPROVAL), HttpStatus.OK);
    }

    @PutMapping(value="/approve-registration-request/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> approveRegistrationRequest(@PathVariable Long id ){
        Patient patient = patientService.approveRegistration(id);

        if (patient == null){
            return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @PutMapping(value="/reject-registration-request/{id}")
    public ResponseEntity<Patient> rejectRegistrationRequest(@RequestBody String message, @PathVariable Long id ){

        boolean reject = patientService.rejectRegistration(id, message);

        if (reject){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/register-clinic")
    public ResponseEntity<Clinic> registerClinic(@RequestBody ClinicDTO clinicDTO){
        Clinic clinic = clinicService.register(clinicDTO);
        if (clinic == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @PostMapping(value="/register-clinic-admin")
    public ResponseEntity<ClinicAdmin> registerClinicAdmin(@RequestBody ClinicAdminDTO clinicAdminDTO){
        ClinicAdmin newClinicAdmin = clinicAdminService.register(clinicAdminDTO);
        if (newClinicAdmin == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newClinicAdmin, HttpStatus.OK);
    }

    @PostMapping(value="/assign-cc-admin/{id}")
    public ResponseEntity<CCAdmin> assignCCAdmin(@PathVariable Long id){
        CCAdmin newCCAdmin = ccAdminService.assign(id);

        if (newCCAdmin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (CCAdmin ccadmin : ccAdminRepository.findAll()){
            if (ccadmin.getEmail() == newCCAdmin.getEmail()){
                return new ResponseEntity<>(newCCAdmin, HttpStatus.BAD_REQUEST);
            }
        }

        newCCAdmin.setUserStatus(UserStatus.NEVER_LOGGED_IN);

        this.ccAdminRepository.save(newCCAdmin);

        return new ResponseEntity<>(newCCAdmin, HttpStatus.OK);
    }

    @GetMapping(value = "/all-ccadmins")
    public List<CCAdmin> getCCAdmins() {
        return ccAdminRepository.findAll();
    }

    @GetMapping(value = "/all-non-ccadmin-accounts")
    public List<Account> getAccounts() {

        List<Account> accounts = accountRepository.findAll();
        List<Account> ret = accountRepository.findAll();;
        for (CCAdmin ccadmin : ccAdminRepository.findAll()){
            for (Account account: accounts){
                if (ccadmin.getEmail().equals(account.getEmail())) {
                    System.out.println(account.getEmail());
                    ret.remove(account);
                }
            }
        }
        return ret;
    }
}
