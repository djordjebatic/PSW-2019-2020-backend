package com.example.pswbackend.services;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.UserRequest;

import java.util.List;

public interface AccountService {

    Account findByEmail(String emailAddress);
    Account findById(Long id);
    List<Account> findAll ();
    Account save(UserRequest accountRequest);
}
