package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Account;
import domain.Patient;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findOne(String emailAddress);
    
    Patient saveNew(Patient newPatient);
}
