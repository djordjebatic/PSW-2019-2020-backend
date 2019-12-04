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
        ccAdmin.setEmail(acc.getEmail());
        ccAdmin.setFirstName(acc.getFirstName());
        ccAdmin.setLastName(acc.getLastName());
        ccAdmin.setPassword(acc.getPassword());
        ccAdmin.setPhoneNumber(acc.getPhoneNumber());

        System.out.println(ccAdmin.getPhoneNumber());

        return ccAdmin;
    }
}
