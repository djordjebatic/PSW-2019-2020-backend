package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.dto.LoginDTO;
import com.example.pswbackend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = GET, value = "/account/{accountId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Account loadById(@PathVariable Long accountId) {
        return this.accountService.findById(accountId);
    }

    @RequestMapping(method = GET, value = "/account/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> loadAll() {
        return this.accountService.findAll();
    }

    @RequestMapping("/whoami")
    @PreAuthorize("hasRole('ACCOUNT')")
    public Account account(Principal account) {
        return this.accountService.findByEmail(account.getName());
    }

}