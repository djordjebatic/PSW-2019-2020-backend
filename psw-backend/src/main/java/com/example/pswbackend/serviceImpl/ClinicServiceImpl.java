package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.dto.FilterClinicsDTO;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pswbackend.repositories.DoctorRepository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public ClinicDTO findById(Long id) {
        Clinic clinic = clinicRepository.findOneById(id);
        if (clinic == null) {
            return null;
        }

        return new ClinicDTO(clinic.getId(), clinic.getName(), clinic.getDescription(), clinic.getAddress(), clinic.getCity());
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

    @Override
    public List<Clinic> filterClinics(FilterClinicsDTO dto) {

        List<Doctor> doctors = doctorRepository.findAll();

        List<Clinic> clinicList= new ArrayList<Clinic>();
        for(Doctor d : doctors){
            if(d.getSpecialization().getId().toString()==dto.getType()){
                int s=0;
                int s1=0;
                for(Appointment a : d.getAppointments()){
                    s1++;
                     System.out.println(dto.getDate());
                    System.out.println(a.getStartDateTime());
                    System.out.println(a.getEndDateTime());
                  /*  if(dto.getDate().before(Date.from(a.getStartDateTime().atZone(ZoneId.systemDefault()).toInstant())) || dto.getDate().after(Date.from(a.getEndDateTime().atZone(ZoneId.systemDefault()).toInstant()))) {
                        s++;
                    }*/
                }
                if(s1==s) {
                    int i=0;
                    for (Clinic c : clinicList) {
                        i++;
                        if (c == d.getClinic()) {
                            break;
                        } else {
                            if (i == clinicList.size()) {
                                clinicList.add(d.getClinic());
                            }
                        }

                    }
                }
            }
        }

        return clinicList;
    }


}
