package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentTypeService {

    AppointmentTypeDTO findById(Long id);
    AppointmentTypeDTO findByName(String name);
    List<AppointmentTypeDTO> findAll();
    List<AppointmentTypeDTO> findByClinicId(Long clinicId);

    Boolean updateType(AppointmentType type, AppointmentTypeDTO dto);
    AppointmentType addNew(AppointmentTypeDTO dto);
    Boolean deleteAppType(Long id);

}
