package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.AppointmentPrice;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.AppointmentPriceDTO;
import com.example.pswbackend.dto.AppointmentPriceFullDTO;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.services.AppointmentPriceService;
import com.example.pswbackend.services.ClinicAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentPriceServiceImpl implements AppointmentPriceService {

    @Autowired
    AppointmentPriceRepository appointmentPriceRepository;

    @Autowired
    ClinicAdminService clinicAdminService;

    public AppointmentPriceDTO getAppointmentPrice(Long specializationId){

        List<AppointmentPrice> priceList = appointmentPriceRepository.findByAppointmentTypeId(specializationId);
        System.out.println(priceList.get(1).getAppointmentEnum().toString());
        System.out.println(priceList.get(1).getPrice());

        AppointmentPriceDTO dto = new AppointmentPriceDTO(priceList.get(1).getAppointmentEnum().toString(),String.valueOf(priceList.get(1).getPrice()));
        return dto;
    }

    @Override
    public List<AppointmentPriceFullDTO> getAll() {
        ClinicAdmin ca = clinicAdminService.getLoggedInClinicAdmin();
        if (ca == null){
            return null;
        }
        Clinic c = ca.getClinic();
        if (c == null){
            return null;
        }
        List<AppointmentPrice> priceList = appointmentPriceRepository.findAll();
        List<AppointmentPriceFullDTO> dtoList = new ArrayList<>();

        for (AppointmentPrice ap: priceList) {
            if (ap.getAppointmentType().getClinic().getId() == c.getId()){
                dtoList.add(new AppointmentPriceFullDTO(ap.getId(), ap.getAppointmentType().getName(),String.valueOf(ap.getPrice()),ap.getAppointmentEnum().name()));
            }
        }

        return dtoList;
    }

}
