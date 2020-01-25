package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import com.example.pswbackend.services.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public AppointmentTypeDTO findById(Long id) {
        AppointmentType appointmentType = appointmentTypeRepository.findOneById(id);
        if (appointmentType == null) {
            return null;
        }

        return new AppointmentTypeDTO(appointmentType.getId(), appointmentType.getName());
    }

    @Override
    public List<AppointmentTypeDTO> findByClinicId(Long clinicId) {
        List<AppointmentType> appointmentType = appointmentTypeRepository.findByClinicId(clinicId);
        if (appointmentType == null) {
            return null;
        }

        List<AppointmentTypeDTO> appDTOL = new ArrayList<AppointmentTypeDTO>();
        for(AppointmentType a :appointmentType){
            AppointmentTypeDTO appDTO = new AppointmentTypeDTO(a.getId(), a.getName());
            appDTOL.add(appDTO);
        }

        return appDTOL;
    }

    @Override
    public AppointmentTypeDTO findByName(String name){
        AppointmentType appointmentType = appointmentTypeRepository.findByName(name);
        if (appointmentType == null) {
            return null;
        }
        return new AppointmentTypeDTO(appointmentType.getId(), appointmentType.getName());
    }

    @Override
    public List<AppointmentTypeDTO> findAll(){
        List<AppointmentType> appointmentType = appointmentTypeRepository.findAll();
        if (appointmentType == null) {
            return null;
        }

        List<AppointmentTypeDTO> appDTOL = new ArrayList<AppointmentTypeDTO>();
        for(AppointmentType a :appointmentType){
            AppointmentTypeDTO appDTO = new AppointmentTypeDTO(a.getId(), a.getName());
            appDTOL.add(appDTO);
        }

        return appDTOL;
    }


}
