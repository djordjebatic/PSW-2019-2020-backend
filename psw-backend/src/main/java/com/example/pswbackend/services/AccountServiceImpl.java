package com.example.pswbackend.services;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Role;
import com.example.pswbackend.domain.UserRequest;
import com.example.pswbackend.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService authService;

    public Account findByEmail(String emailAddress){
        return accountRepository.findByEmail(emailAddress);
    }

    public Account findById(Long id) throws AccessDeniedException {
        Account account = accountRepository.findById(id).orElseGet(null);
        return account;
    }

    public List<Account> findAll() throws AccessDeniedException {
        List<Account> result = accountRepository.findAll();
        return result;
    }

    // za pacijenta ili za sve???
    public Account save(AccountRequest accountRequest) {
        Account account = new Account();
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        account.setFirstName(accountRequest.getFirstName());
        account.setLastName(accountRequest.getLastName());
        account.setEmail(accountRequest.getEmail());
        account.setAddress(accountRequest.getAddress());
        account.setCity(accountRequest.getCity());
        account.setCountry(accountRequest.getCountry());
        account.setPhoneNumber(accountRequest.getPhoneNumber());
        account.setEnabled(true);

        List<Role> auth = authService.findByName("PATIENT");
        account.setAuthorities(auth);

        account = this.accountRepository.save(account);
        return account;
    }

}

