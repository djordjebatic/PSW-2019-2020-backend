package com.example.pswbackend.services;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;

import java.util.List;

public interface ClinicService {

    ClinicDTO findById(Long id);
    Clinic findByName(String name);

    Clinic register(ClinicDTO clinicDTO);

    List<Clinic> findAll();
}
