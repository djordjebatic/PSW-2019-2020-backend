package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.*;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
    DiagnosisRepository diagnosisRepository;

    @Autowired
    DrugRepository drugRepository;

    @Autowired
    CCAdminRepository ccAdminRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    CodebookService codebookService;

    @GetMapping(value="/all-registration-requests")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<List<RegisterApprovalDTO>> getAllRegistrationRequests() {
        return new ResponseEntity<>(patientService.findByStatus(Status.AWAITING_APPROVAL), HttpStatus.OK);
    }

    @PutMapping(value="/send-verification-email/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Patient> sendVerificationEmail(@PathVariable Long id ){
        Patient patient = patientRepository.findOneById(id);
        if (patient == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean bool = patientService.sendVerificationEmail(id);

        if (bool){
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
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
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Patient> rejectRegistrationRequest(@RequestBody String message, @PathVariable Long id ){

        boolean reject = patientService.rejectRegistration(id, message);

        if (reject){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get-all-clinics")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity getAllClinics(){

        List<Clinic> clinics = clinicService.findAll();
        if (clinics.isEmpty()){
            return new ResponseEntity<>("There are no clinics registered in clinic center system", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @PostMapping(value="/register-clinic")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity registerClinic(@RequestBody ClinicDTO clinicDTO){

        Clinic clinic = clinicService.register(clinicDTO);
        if (clinic == null){
            return new ResponseEntity<>("Clinic with given name already exists at a given address", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @PostMapping(value="/register-clinic-admin")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity registerClinic(@RequestBody ClinicAdminDTO clinicAdminDTO){

        ClinicAdmin newClinicAdmin = clinicAdminService.register(clinicAdminDTO);
        if (newClinicAdmin == null){
            return new ResponseEntity<>("User with given email address already exists", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newClinicAdmin, HttpStatus.OK);
    }

    @GetMapping(value="/get-all-diagnosis")
    @PreAuthorize("hasAnyRole('CC_ADMIN', 'DOCTOR')")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis() {
        return new ResponseEntity<>(diagnosisRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }

    @PostMapping(value = "/add-diagnosis")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO){

        if (diagnosisRepository.findByName(diagnosisDTO.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Diagnosis newDiagnosis = codebookService.saveDiagnosis(diagnosisDTO);
            return new ResponseEntity<>(newDiagnosis, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update-diagnosis/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody DiagnosisDTO diagnosisDTO){

        Diagnosis diagnosis = diagnosisRepository.findOneById(id);

        if (diagnosis == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean success = codebookService.updateDiagnosis(diagnosis, diagnosisDTO);

        if(success) {
            return new ResponseEntity<>(diagnosis, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/delete-diagnosis/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> deleteDiagnosis(@PathVariable Long id){

        Boolean success = codebookService.deleteDiagnosis(id);

        if (success){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/get-all-drugs")
    @PreAuthorize("hasAnyRole('CC_ADMIN', 'DOCTOR')")
    public ResponseEntity<List<Drug>> getAllDrugs() {
        return new ResponseEntity<>(drugRepository.findAllByOrderByIdAsc(), HttpStatus.OK);

    }

    @PostMapping(value = "/add-drug")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Drug> addDrug(@RequestBody DrugDTO drugDTO){

        if (drugRepository.findByName(drugDTO.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Drug newDrug = codebookService.saveDrug(drugDTO);
            return new ResponseEntity<>(newDrug, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update-drug/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity updateDrug(@PathVariable Long id, @RequestBody DrugDTO drugDTO){

        Drug drug = drugRepository.findOneById(id);

        if (drug == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Drug updated = codebookService.updateDrug(drug, drugDTO);
            if (updated == null){
                return new ResponseEntity<>("Something went wrong. Please try again later.", HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/delete-drug/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Drug> deleteDrug(@PathVariable Long id){

        Boolean success = codebookService.deleteDrug(id);

        if (success){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/register-cc-admin")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity registerCCAdmin(@RequestBody CCAdminDTO ccAdminDTO){

        CCAdmin ccAdmin = ccAdminService.register(ccAdminDTO);
        if (ccAdmin == null){
            return new ResponseEntity<>("User with given email address already exists", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(ccAdmin, HttpStatus.OK);
    }

    @PostMapping(value="/assign-cc-admin/{id}")
    public ResponseEntity<CCAdmin> assignCCAdmin(@PathVariable Long id){

        CCAdmin newCCAdmin = ccAdminService.assign(id);

        if (newCCAdmin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (CCAdmin ccadmin : ccAdminRepository.findAll()){
            if (ccadmin.getUsername() == newCCAdmin.getUsername()){
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
        List<Account> ret = accountRepository.findAll();

        // checks if account has a ccadmin role and then removes accounts with same email
        for (Account account : accounts){
            if (account.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CC_ADMIN"))){
                for (Account potentialOther : accounts){
                    if (potentialOther.getUsername().equals(account.getUsername()) && !potentialOther.getAuthorities().stream()
                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CC_ADMIN"))){
                        ret.remove(potentialOther);
                    }
                }
                ret.remove(account);
            }
            if (account instanceof Patient){
                if (((Patient) account).getPatientStatus().equals(Status.AWAITING_APPROVAL)){
                    ret.remove(account);
                }
            }
        }

        return ret;
    }
}
