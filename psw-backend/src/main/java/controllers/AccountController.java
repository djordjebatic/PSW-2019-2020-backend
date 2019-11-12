package controllers;

import domain.Account;
import dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    //private AccountService accountService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> login(UserLoginDTO loginDTO) {
        // Account account = service.getOne();
        // dole prosledi
        return new ResponseEntity<Account>(HttpStatus.OK);
    }
}
