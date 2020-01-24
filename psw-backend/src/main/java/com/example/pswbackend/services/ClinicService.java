package com.example.pswbackend.services;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.FilterClinicsDTO;
import com.example.pswbackend.dto.ResultClinicDTO;

import java.util.List;

public interface ClinicService {

    ClinicDTO findById(Long id);
    List<Clinic> findByName(String name);

    Clinic register(ClinicDTO clinicDTO);

    List<Clinic> findAll();
    List<ResultClinicDTO> filterClinics(FilterClinicsDTO dto);
}
