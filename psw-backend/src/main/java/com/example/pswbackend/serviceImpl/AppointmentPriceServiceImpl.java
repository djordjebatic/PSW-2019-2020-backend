package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.AppointmentPrice;
import com.example.pswbackend.dto.AppointmentPriceDTO;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.services.AppointmentPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentPriceServiceImpl implements AppointmentPriceService {

    @Autowired
    AppointmentPriceRepository appointmentPriceRepository;

    public AppointmentPriceDTO getAppointmentPrice(Long specializationId){

        List<AppointmentPrice> priceList = appointmentPriceRepository.findByAppointmentTypeId(specializationId);
        System.out.println(priceList.get(1).getAppointmentEnum().toString());
        System.out.println(priceList.get(1).getPrice());

        AppointmentPriceDTO dto = new AppointmentPriceDTO(priceList.get(1).getAppointmentEnum().toString(),String.valueOf(priceList.get(1).getPrice()));
        return dto;
    }

}
