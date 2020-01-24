package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.repositories.ClinicAdminRepository;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    ClinicAdminRepository clinicAdminRepository;

    @Override
    public ClinicDTO findById(Long id) {
        Clinic clinic = clinicRepository.findOneById(id);
        if (clinic == null) {
            return null;
        }

        return new ClinicDTO(clinic.getId(), clinic.getName(), clinic.getDescription(), clinic.getAddress(), clinic.getCity());
    }

    @Override
    public Clinic findClinicById(Long id) {
        Clinic clinic = clinicRepository.findOneById(id);
        if (clinic == null) {
            return null;
        }

        return clinic;
    }

    @Override
    public Long findByClinicAdminId(long clinicAdminId){
        ClinicAdmin ca = clinicAdminRepository.findOneById(clinicAdminId);
        return ca.getClinic().getId();
    }

    @Override
    public Clinic findByName(String name) {
        return clinicRepository.findByName(name);
    }

    @Override
    public Clinic register(ClinicDTO clinicDTO) {
        if (findByName(clinicDTO.getName()) != null){
            System.out.println("Clinic with name: %s" + clinicDTO.getName() + "already exists.");
            return null;
        }

        Clinic clinic = new Clinic(clinicDTO.getName(), clinicDTO.getDescription(), clinicDTO.getAddress(), clinicDTO.getCity());

        return clinicRepository.save(clinic);
    }

    @Override
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }
}
