package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.AccountRequest;
import com.example.pswbackend.domain.AccountTokenState;
import com.example.pswbackend.exception.ResourceConflictException;
import com.example.pswbackend.security.TokenUtils;
import com.example.pswbackend.services.AccountService;
import com.example.pswbackend.services.CustomAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(method = POST, value = "/signup")
    public ResponseEntity<?> addUser(@RequestBody AccountRequest accountRequest, UriComponentsBuilder ucBuilder) {

        Account existAccount = this.accountService.findByEmail(accountRequest.getEmail());
        if (existAccount != null) {
            throw new ResourceConflictException(accountRequest.getId(), "Email already exists");
        }

        Account account = this.accountService.save(accountRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/account/{accountId}").buildAndExpand(account.getId()).toUri());
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
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

}
