package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Authority;
import com.example.pswbackend.domain.CCAdmin;
import com.example.pswbackend.dto.CCAdminDTO;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.CCAdminRepository;
import com.example.pswbackend.services.CCAdminService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CCAdminServiceImpl implements CCAdminService {

    @Autowired
    CCAdminRepository ccAdminRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmailService emailService;

    @Override
    public CCAdmin findByName(String name) {
        CCAdmin ccAdmin = ccAdminRepository.findByEmail(name);

        return  ccAdmin;
    }

    @Override
    public CCAdmin changePassword(String newPassword, CCAdmin ccAdmin) {
        ccAdmin.setPassword(newPassword);
        if (ccAdmin.getUserStatus().equals(UserStatus.NEVER_LOGGED_IN)) {
            ccAdmin.setUserStatus(UserStatus.ACTIVE);
        }
        return ccAdminRepository.save(ccAdmin);
    }

    @Override
    public CCAdmin assign(Long id) {

        CCAdmin ccAdmin = new CCAdmin();

        Account acc = accountRepository.findById(id).get();
        ccAdmin.setAddress(acc.getAddress());
        ccAdmin.setCity(acc.getCity());
        ccAdmin.setCountry(acc.getCountry());
        ccAdmin.setEmail(acc.getUsername());
        ccAdmin.setFirstName(acc.getFirstName());
        ccAdmin.setLastName(acc.getLastName());
        ccAdmin.setPassword(acc.getPassword());
        ccAdmin.setPhoneNumber(acc.getPhoneNumber());

        return ccAdmin;
    }

    @Override
    public CCAdmin register(CCAdminDTO ccAdminDTO) {

        if (accountRepository.findByEmail(ccAdminDTO.getEmail()) != null){
            return null;
        }

        CCAdmin ccAdmin = new CCAdmin();
        ccAdmin.setFirstName(ccAdminDTO.getFirstName());
        ccAdmin.setLastName(ccAdminDTO.getLastName());
        ccAdmin.setPhoneNumber(ccAdminDTO.getPhoneNumber());
        ccAdmin.setEmail(ccAdminDTO.getEmail());
        ccAdmin.setCountry(ccAdminDTO.getCountry());
        ccAdmin.setCity(ccAdminDTO.getCity());
        ccAdmin.setAddress(ccAdminDTO.getAddress());
        ccAdmin.setPassword("$2y$12$4zrqOojpixOe/ogFw1xyyuQuIvFqrzbj0IohYtshqqy1P5rS6kdbq");
        ccAdmin.setUserStatus(UserStatus.NEVER_LOGGED_IN);
        List<Authority> authorities = new ArrayList<>();
        Authority a = new Authority();
        a.setName("ROLE_CC_ADMIN");
        a.setId(5L);
        authorities.add(a);
        ccAdmin.setAuthorities(authorities);

        String s = "You have been registered as an Clinic Center Admin. You can now log in to the Clinical Centre System. " +
                "Please visit https://psw-isa-tim3-frontend.herokuapp.com/login to log into your account. " +
                "Your default password is \"admin\" and you will need to change it upon initial log in.";
        emailService.sendEmail(ccAdmin.getUsername(), "Registration Request Response", s);

        return ccAdminRepository.save(ccAdmin);
    }
}