package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String emailAddress);

}