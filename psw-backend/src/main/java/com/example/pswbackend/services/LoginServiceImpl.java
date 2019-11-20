package com.example.pswbackend.services;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginRepository loginRepo;

    public Account findByEmail(String emailAddress){
        return loginRepo.findByEmail(emailAddress);
    }

}

