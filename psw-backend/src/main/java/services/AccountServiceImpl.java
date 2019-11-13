package services;

import domain.Account;
import dto.UserLoginDTO;
import org.springframework.stereotype.Service;
import repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    public Account loginUser(UserLoginDTO loginDTO){

        Account foundAccount = AccountRepository.findOne(loginDTO.getEmailAddress());

        return null;
    }
}
