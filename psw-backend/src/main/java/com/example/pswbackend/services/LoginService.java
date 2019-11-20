package com.example.pswbackend.services;

import com.example.pswbackend.domain.Account;

public interface LoginService {

    Account findByEmail(String emailAddress);

}
