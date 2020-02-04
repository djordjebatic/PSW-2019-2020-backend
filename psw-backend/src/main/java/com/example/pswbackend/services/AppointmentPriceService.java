package com.example.pswbackend.services;

import com.example.pswbackend.domain.AppointmentPrice;
import com.example.pswbackend.dto.AppointmentPriceDTO;
import com.example.pswbackend.dto.AppointmentPriceFullDTO;

import java.util.List;

public interface AppointmentPriceService {

    AppointmentPriceDTO getAppointmentPrice(Long specializationId);
    List<AppointmentPriceFullDTO> getAll();
}
