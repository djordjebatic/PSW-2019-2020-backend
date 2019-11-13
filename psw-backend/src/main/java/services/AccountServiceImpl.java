package services;

import org.springframework.stereotype.Service;

import domain.Account;
import dto.UserLoginDTO;
import dto.UserSignUpDTO;
import repositories.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {

    public Account loginUser(UserLoginDTO loginDTO){

        Account foundAccount = AccountRepository.findOne(loginDTO.getEmailAddress());

        return null;
    }
    
    public Account signUpUser(UserSignUpDTO signUpDTO) {
    	
    	return null;
    }
}
