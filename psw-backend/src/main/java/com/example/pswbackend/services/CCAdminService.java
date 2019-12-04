package com.example.pswbackend.services;

import com.example.pswbackend.domain.CCAdmin;
import com.example.pswbackend.dto.CCAdminDTO;

import java.util.List;

public interface CCAdminService {

    CCAdmin findByName(String name);

    CCAdmin changePassword(String newPassword, CCAdmin ccAdmin);

    CCAdmin assign(Long id);

}