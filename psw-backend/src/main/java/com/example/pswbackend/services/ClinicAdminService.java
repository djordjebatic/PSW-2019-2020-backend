package com.example.pswbackend.services;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.ClinicAdminDTO;

import java.util.List;

public interface ClinicAdminService {
    ClinicAdminDTO findByName(String name);

    ClinicAdmin register(ClinicAdminDTO clinicAdminDTO);

    List<ClinicAdmin> findAll();
}
