package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import com.example.pswbackend.services.AppointmentTypeService;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    private ClinicService clinicService;

    @Override
    public AppointmentTypeDTO findById(Long id) {
        AppointmentType appointmentType = appointmentTypeRepository.findOneById(id);
        if (appointmentType == null) {
            return null;
        }

        return new AppointmentTypeDTO(appointmentType.getId(), appointmentType.getName());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public AppointmentType addNew(AppointmentTypeDTO dto){

        try {
            Long clinicId = dto.getClinicId();
            Clinic c = clinicService.findClinicById(clinicId);
            AppointmentType type = new AppointmentType(dto.getName(), c);
            c.getAppointmentTypes().add(type);

            return type;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean updateType(AppointmentType type, AppointmentTypeDTO dto){

        try{
            type.setName(dto.getName());
            appointmentTypeRepository.save(type);
        }
        catch(Exception e){
            return false;
        }

        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean deleteAppType(Long id){

        try {
            appointmentTypeRepository.deleteOneById(id);
            return true;
        } catch (Exception e){
            return true; //exception jer delete ne vraca nista -> PSQLException: No results were returned by the query
        }

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
