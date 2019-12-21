package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.PatientDTO;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.exception.ResourceConflictException;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.security.TokenUtils;
import com.example.pswbackend.security.auth.JwtAuthenticationRequest;
import com.example.pswbackend.services.AccountService;
import com.example.pswbackend.services.CustomAccountDetailsService;
import com.example.pswbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomAccountDetailsService accountDetailsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) throws AuthenticationException, IOException {

        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Account account = (Account) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(account.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new AccountTokenState(jwt, expiresIn));
    }

    @RequestMapping(method = POST, value = "/signup")
    public ResponseEntity<?> addUser(@RequestBody AccountRequest accountRequest, UriComponentsBuilder ucBuilder) {

        Account existAccount = this.accountService.findByEmail(accountRequest.getEmail());
        if (existAccount != null) {
            throw new ResourceConflictException(accountRequest.getId(), "Email already exists");
        }

        Account account = this.accountService.save(accountRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/account/{accountId}").buildAndExpand(account.getId()).toUri());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getEmailFromToken(token);
        Account account = (Account) this.accountDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, account.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new AccountTokenState(refreshedToken, expiresIn));
        } else {
            AccountTokenState userTokenState = new AccountTokenState();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }

    @RequestMapping(value = "/getMyUser", method = RequestMethod.GET)
    public ResponseEntity<?> getMyUser(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getEmailFromToken(token);
        Account account = (Account) this.accountDetailsService.loadUserByUsername(username);
        return new ResponseEntity<>(account, HttpStatus.OK);

    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        accountDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.accepted().body(result);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientService.registerPatient(patientDTO);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }
}
