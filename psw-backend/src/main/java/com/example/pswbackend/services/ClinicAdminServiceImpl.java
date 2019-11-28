package com.example.pswbackend.services;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.ClinicAdminDTO;
import com.example.pswbackend.enums.UserStatus;
import com.example.pswbackend.repositories.ClinicAdminRepository;
import com.example.pswbackend.repositories.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicAdminServiceImpl implements ClinicAdminService{

    @Autowired
    ClinicAdminRepository clinicAdminRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    EmailService emailService;

    @Override
    public ClinicAdminDTO findByName(String name) {
        ClinicAdminDTO clinicAdminDTO = clinicAdminRepository.findByEmail(name);
        if (clinicAdminDTO == null) {
            return null;
        }

        return new ClinicAdminDTO(clinicAdminDTO.getEmail(), clinicAdminDTO.getPassword(), clinicAdminDTO.getFirstName(), clinicAdminDTO.getLastName(), clinicAdminDTO.getPhoneNumber(), clinicAdminDTO.getAddress(), clinicAdminDTO.getCity(), clinicAdminDTO.getCountry(), clinicAdminDTO.getClinicId());
    }

    @Override
    public ClinicAdmin register(ClinicAdminDTO clinicAdminDTO) {

        ClinicAdmin clinicAdmin = new ClinicAdmin();
        clinicAdmin.setFirstName(clinicAdminDTO.getFirstName());
        clinicAdmin.setLastName(clinicAdminDTO.getLastName());
        clinicAdmin.setPhoneNumber(clinicAdminDTO.getPhoneNumber());
        clinicAdmin.setEmail(clinicAdminDTO.getEmail());
        clinicAdmin.setAddress(clinicAdminDTO.getAddress());
        clinicAdmin.setCity(clinicAdminDTO.getCity());
        clinicAdmin.setCountry(clinicAdminDTO.getCountry());
        clinicAdmin.setPassword(clinicAdminDTO.getPassword());
        clinicAdmin.setClinic(clinicRepository.findOneById(clinicAdminDTO.getClinicId()));
        clinicAdmin.setStatus(UserStatus.NEVER_LOGGED_IN);
        if (clinicAdminRepository.findByEmail(clinicAdmin.getEmail()) != null){
            System.out.println("Vec postoji");
            return null;
        }

        String s = "You have been registered as an Admin of %s" + clinicAdmin.getClinic().getName() + " clinic! You can now log in to the Clinical Centre System";
        //emailService.sendEmail(clinicAdmin.getEmail(), "Registration Request Response", s);

        return clinicAdminRepository.save(clinicAdmin);

    }
  
    @Override
    public boolean receiveAppointmentRequest(AppointmentDoctorDTO dto) {

        // proverava da li moze da zakaze

        return false;

    //TODO
    @Override
    public List<ClinicAdmin> findAll() {
        return null;

    }
}
