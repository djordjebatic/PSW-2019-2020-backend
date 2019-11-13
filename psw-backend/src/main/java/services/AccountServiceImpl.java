package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Account;
import domain.Patient;
import dto.UserLoginDTO;
import dto.UserSignUpDTO;
import repositories.AccountRepository;


@Service
public class AccountServiceImpl implements services.AccountService {
	
    @Autowired
    private AccountRepository accountRepo;

        Account foundAccount = accountRepo.findOne(loginDTO.getEmailAddress());

        if (foundAccount.getPassword() != loginDTO.getPassword()){
            return null;
        }

        return foundAccount;
        
    }

    public Patient signUpUser(UserSignUpDTO signUpDTO) {
    	
    	Account foundAccount = accountRepo.findOne(signUpDTO.getEmail());
    	
    	Patient newPatient= new Patient();
   
    	newPatient.setEmail(signUpDTO.getEmail());
    	newPatient.setPassword(signUpDTO.getPassword());
    	newPatient.setFirstName(signUpDTO.getFirstName());
    	newPatient.setLastName(signUpDTO.getLastName());
    	newPatient.setAddress(signUpDTO.getAddress());
    	newPatient.setCity(signUpDTO.getCity());
    	newPatient.setCountry(signUpDTO.getCountry());
    	newPatient.setPhoneNumber(signUpDTO.getPhoneNumber());
    	newPatient.setMedicalNumber(signUpDTO.getMedicalNumber());

    	
    	return null;
    }
}
