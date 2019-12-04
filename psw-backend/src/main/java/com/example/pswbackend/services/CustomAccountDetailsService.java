package com.example.pswbackend.services;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.repositories.AccountRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAccountDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account acc = accountRepo.findByEmail(email);
        if (acc == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return acc;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + email + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '" + email + "'");

        Account acc = (Account) loadUserByUsername(email);

        acc.setPassword(passwordEncoder.encode(newPassword));
        accountRepo.save(acc);

    }

}
