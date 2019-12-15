package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.CCAdminRepository;
import com.example.pswbackend.repositories.DiagnosisRepository;
import com.example.pswbackend.repositories.DrugRepository;
import com.example.pswbackend.services.CCAdminService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.PatientService;
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

    @GetMapping(value="/all-registration-requests")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<List<RegisterApprovalDTO>> getAllRegistrationRequests() {
        return new ResponseEntity<>(patientService.findByStatus(Status.AWAITING_APPROVAL), HttpStatus.OK);
    }

    @PutMapping(value="/approve-registration-request/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CC_ADMIN')")
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/register-clinic")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Clinic> registerClinic(@RequestBody ClinicDTO clinicDTO){
        Clinic clinic = clinicService.register(clinicDTO);
        if (clinic == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @PostMapping(value="/register-clinic-admin")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<ClinicAdmin> registerClinic(@RequestBody ClinicAdminDTO clinicAdminDTO){
        ClinicAdmin newClinicAdmin = clinicAdminService.register(clinicAdminDTO);
        if (newClinicAdmin == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newClinicAdmin, HttpStatus.OK);
    }

    @GetMapping(value="/all-diagnosis")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis() {
        return new ResponseEntity<>(diagnosisRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/add-diagnosis")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody DiagnosisDTO diagnosisDTO){
        Diagnosis diagnosis = new Diagnosis(diagnosisDTO.getName(), diagnosisDTO.getDescription());

        if (diagnosisRepository.findByName(diagnosis.getName()) == null) {
            diagnosisRepository.save(diagnosis);
            return new ResponseEntity<>(diagnosis, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-diagnosis/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody DiagnosisDTO diagnosisDTO){

        Diagnosis diagnosis = diagnosisRepository.findOneById(id);

        if (diagnosis == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        diagnosis.setName(diagnosisDTO.getName());
        diagnosis.setDescription(diagnosisDTO.getDescription());
        diagnosisRepository.save(diagnosis);

        return new ResponseEntity<>(diagnosis, HttpStatus.OK);

    }

    @PutMapping(value = "/delete-diagnosis/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Diagnosis> deleteDiagnosis(@PathVariable Long id){

        Diagnosis diagnosis = diagnosisRepository.findOneById(id);

        if (diagnosis == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        diagnosisRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/get-all-drugs")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<List<Drug>> getAllDrugs() {
        return new ResponseEntity<>(drugRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/add-drug")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Drug> addDrug(@RequestBody DrugDTO drugDTO){
        Drug drug = new Drug(drugDTO.getName(), drugDTO.getIngredient(), drugDTO.getDescription());

        if (drugRepository.findByName(drug.getName()) == null) {
            drugRepository.save(drug);
            return new ResponseEntity<>(drug, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-drug/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Drug> updateDrug(@PathVariable Long id, @RequestBody DrugDTO drugDTO){

        Drug drug = drugRepository.findOneById(id);

        if (drug == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        drug.setName(drugDTO.getName());
        drug.setDescription(drugDTO.getDescription());
        drug.setIngredient(drugDTO.getIngredient());

        drugRepository.save(drug);
        return new ResponseEntity<>(drug, HttpStatus.OK);
    }

    @PutMapping(value = "/delete-drug/{id}")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<Drug> deleteDrug(@PathVariable Long id){

        Drug drug = drugRepository.findOneById(id);

        if (drug == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        drugRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value="/register-cc-admin")
    @PreAuthorize("hasRole('CC_ADMIN')")
    public ResponseEntity<CCAdmin> registerCCAdmin(@RequestBody CCAdminDTO ccAdminDTO){
        CCAdmin ccAdmin = ccAdminService.register(ccAdminDTO);
        if (ccAdmin == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        ccAdmin.setUserStatus(UserStatus.NEVER_LOGGED_IN);
        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority();
        a.setName("ROLE_CC_ADMIN");
        a.setId(new Long(5));
        authorities.add(a);
        ccAdmin.setAuthorities(authorities);

        ccAdminRepository.save(ccAdmin);

        return new ResponseEntity<>(ccAdmin, HttpStatus.OK);
    }

    @PostMapping(value="/assign-cc-admin/{id}")
    public ResponseEntity<CCAdmin> assignCCAdmin(@PathVariable Long id){
        Account acc = accountRepository.findById(id).get();


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
