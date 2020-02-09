package com.example.pswbackend.services;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.AppointmentRequestDTO;

import java.util.List;

public interface AppointmentRequestService {

    boolean saveRequest(AppointmentDoctorDTO dto, Clinic c);
    AppointmentRequestDTO getById(Long id);

    boolean sendRequest(AppointmentRequestDTO dto);

    List<AppointmentRequestDTO> getClinicAppReqCA();
    List<AppointmentRequestDTO> getClinicAppReqDoc();
}
