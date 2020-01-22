package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Override
    public ClinicDTO findById(Long id) {
        Clinic clinic = clinicRepository.findOneById(id);
        if (clinic == null) {
            return null;
        }

        return new ClinicDTO(clinic.getId(), clinic.getName(), clinic.getDescription(), clinic.getAddress(), clinic.getCity());
    }

    @Override
    public List<Clinic> findByName(String name) {
        return clinicRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Clinic register(ClinicDTO clinicDTO) {

        List<Clinic> clinics = findByName(clinicDTO.getName());
        for (Clinic clinic : clinics){
            if (clinic.getAddress().toUpperCase().equals(clinicDTO.getAddress().toUpperCase()) && clinic.getCity().toUpperCase().equals(clinicDTO.getCity().toUpperCase())){
                return null;
            }
        }

        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress(), clinicDTO.getCity());

        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }
}
