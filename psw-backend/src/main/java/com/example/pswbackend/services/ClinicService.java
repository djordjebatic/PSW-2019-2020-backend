package com.example.pswbackend.services;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.FilterClinicsDTO;

import java.util.List;

public interface ClinicService {

    ClinicDTO findById(Long id);
    Clinic findByName(String name);

    Clinic register(ClinicDTO clinicDTO);

    List<Clinic> findAll();
    List<Clinic> filterClinics(FilterClinicsDTO dto);
}
