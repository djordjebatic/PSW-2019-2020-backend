package com.example.pswbackend.services;

import com.example.pswbackend.dto.AppointmentTypeDTO;

import java.util.List;

public interface AppointmentTypeService {

    AppointmentTypeDTO findById(Long id);
    AppointmentTypeDTO findByName(String name);
    List<AppointmentTypeDTO> findAll();
    List<AppointmentTypeDTO> findByClinicId(Long clinicId);

}
