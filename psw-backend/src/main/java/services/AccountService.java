package services;

import domain.Account;
import dto.UserLoginDTO;
import dto.UserSignUpDTO;


public interface AccountService {

    Account loginUser(UserLoginDTO loginDTO);
    
    Account signUpUser(UserSignUpDTO signUpDTO);
    
}
