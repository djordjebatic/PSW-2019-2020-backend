package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.dto.PatientDTO;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = GET, value = "/account/{accountId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Account loadById(@PathVariable Long accountId) {
        return this.accountService.findById(accountId);
    }

    @RequestMapping(method = GET, value = "/account/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Account> loadAll() {
        return this.accountService.findAll();
    }

    @RequestMapping("/whoami")
    @PreAuthorize("hasAuthority('ACCOUNT')")
    public Account account(Principal account) {
        return this.accountService.findByEmail(account.getName());
    }

    @PutMapping(value="/update-personal-info/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('DOCTOR') or hasRole('PATIENT') or hasRole('CLINIC_ADMIN') or hasRole('CC_ADMIN') or hasRole('NURSE')")
    public Account updateAccount(@PathVariable long id,@RequestBody PatientDTO dto){

        Account account= accountService.findById(id);

        if(account==null){
            return null;
        }

        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setAddress(dto.getAddress());
        account.setPhoneNumber(dto.getPhoneNumber());
        account.setCity(dto.getCity());
        account.setCountry(dto.getCountry());
        //account.setPassword(dto.getPassword());

        accountRepository.save(account);

        return account;
    }

}