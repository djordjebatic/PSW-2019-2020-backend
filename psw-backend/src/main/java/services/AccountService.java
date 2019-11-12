package services;

import domain.Account;
import dto.UserLoginDTO;

public interface AccountService {

    Account loginUser(UserLoginDTO loginDTO);

}
