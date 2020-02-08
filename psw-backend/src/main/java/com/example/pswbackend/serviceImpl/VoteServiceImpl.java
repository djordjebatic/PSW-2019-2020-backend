package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.VoteDTO;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteDoctorRepository voteDoctorRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    VoteClinicRepository voteClinicRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Override
    public VoteDoctor voteForDoctor(VoteDTO dto){

        if(dto==null){
            return null;
        }
        VoteDoctor voteDoctor= voteDoctorRepository.findByPatientIdAndDoctorId(Long.parseLong(dto.getPatientId()), Long.parseLong(dto.getForId()));

        if(voteDoctor==null){
            VoteDoctor voteDoctor1= new VoteDoctor();
            Doctor d= doctorRepository.findOneById(Long.parseLong(dto.getForId()));
            Patient p= patientRepository.findOneById(Long.parseLong(dto.getPatientId()));
            voteDoctor1.setDoctor(d);
            voteDoctor1.setPatient(p);
            voteDoctor1.setStars(Long.parseLong(dto.getRating()));

            voteDoctorRepository.save(voteDoctor1);
            return voteDoctor1;

        }else{
            voteDoctor.setStars(Long.parseLong(dto.getRating()));
            voteDoctorRepository.save(voteDoctor);
            return voteDoctor;
        }
    }

    @Override
    public VoteClinic voteForClinic(VoteDTO dto){

        if(dto==null){
            return null;
        }
        VoteClinic voteClinic= voteClinicRepository.findByPatientIdAndClinicId(Long.parseLong(dto.getPatientId()), Long.parseLong(dto.getForId()));

        if(voteClinic==null){
            VoteClinic voteClinic1= new VoteClinic();
            Clinic c= clinicRepository.findOneById(Long.parseLong(dto.getForId()));
            Patient p= patientRepository.findOneById(Long.parseLong(dto.getPatientId()));
            voteClinic1.setClinic(c);
            voteClinic1.setPatient(p);
            voteClinic1.setStars(Long.parseLong(dto.getRating()));

            voteClinicRepository.save(voteClinic1);
            return voteClinic1;

        }else{
            voteClinic.setStars(Long.parseLong(dto.getRating()));
            voteClinicRepository.save(voteClinic);
            return voteClinic;
        }
    }
}
