package com.example.pswbackend.services;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.CCAdmin;
import com.example.pswbackend.dto.CCAdminDTO;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.CCAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        CCAdmin ccAdmin = new CCAdmin();
        ccAdmin.setFirstName(ccAdminDTO.getFirstName());
        ccAdmin.setLastName(ccAdminDTO.getLastName());
        ccAdmin.setPhoneNumber(ccAdminDTO.getPhoneNumber());
        ccAdmin.setEmail(ccAdminDTO.getEmail());
        ccAdmin.setCountry(ccAdminDTO.getCountry());
        ccAdmin.setCity(ccAdminDTO.getCity());
        ccAdmin.setAddress(ccAdminDTO.getAddress());
        ccAdmin.setPassword(ccAdminDTO.getPassword());
        ccAdmin.setUserStatus(UserStatus.NEVER_LOGGED_IN);
        if (ccAdminRepository.findByEmail(ccAdmin.getUsername()) != null){
            System.out.println("Vec postoji");
            return null;
        }

        String s = "You have been registered as an Clinic Center Admin. You can now log in to the Clinical Centre System";
        emailService.sendEmail(ccAdmin.getUsername(), "Registration Request Response", s);

        return ccAdminRepository.save(ccAdmin);

    }
}