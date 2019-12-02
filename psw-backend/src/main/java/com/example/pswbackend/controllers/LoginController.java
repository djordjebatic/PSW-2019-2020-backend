package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.dto.LoginDTO;
import com.example.pswbackend.repositories.LoginRepository;
import com.example.pswbackend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/accounts")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping(value="/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> login(@RequestBody LoginDTO dto) {

        Account acc = this.loginService.findByEmail(dto.getEmailAddress());

        if (acc == null){
            System.out.println("Wrong password or email");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (dto.getPassword().equals(acc.getPassword())) {
            System.out.println("Login successful");
            return new ResponseEntity<>(acc, HttpStatus.OK);
        }
        else {
            System.out.println("Wrong password or email");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}