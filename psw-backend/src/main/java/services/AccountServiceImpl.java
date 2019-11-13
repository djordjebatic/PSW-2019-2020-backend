package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Account;
import dto.UserLoginDTO;
import dto.UserSignUpDTO;
import repositories.AccountRepository;


@Service
public class AccountServiceImpl implements services.AccountService {

    @Autowired
    private AccountRepository accountRepo;

    public Account loginUser(UserLoginDTO loginDTO){


        Account foundAccount = accountRepo.findOne(loginDTO.getEmailAddress());

        if (foundAccount.getPassword() != loginDTO.getPassword()){
            return null;
        }

        return foundAccount;
    }
    
    public Account signUpUser(UserSignUpDTO signUpDTO) {
    	
    	return null;
    }
}
