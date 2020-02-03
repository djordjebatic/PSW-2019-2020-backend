package com.example.pswbackend.services;

import com.example.pswbackend.dto.AppointmentPriceDTO;

public interface AppointmentPriceService {

    AppointmentPriceDTO getAppointmentPrice(Long specializationId);
}
