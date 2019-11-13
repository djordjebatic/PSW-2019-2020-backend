package services;

import org.springframework.stereotype.Service;

import domain.Account;
import dto.UserLoginDTO;
import dto.UserSignUpDTO;
import repositories.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {

<<<<<<< Updated upstream
    public Account loginUser(UserLoginDTO loginDTO){

        Account foundAccount = AccountRepository.findOne(loginDTO.getEmailAddress());
=======
    @Autowired
    private AccountRepository accountRepo;
	
    public Account loginUser(UserLoginDTO loginDTO){

        Account foundAccount = accountRepo.findOne(loginDTO.getEmailAddress());
>>>>>>> Stashed changes

        if (foundAccount.getPassword() != loginDTO.getPassword()){
            return null;
        }

        return foundAccount;
    }
    
<<<<<<< Updated upstream
    public Account signUpUser(UserSignUpDTO signUpDTO) {
=======
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
>>>>>>> Stashed changes
    	
    	return null;
    }
}
