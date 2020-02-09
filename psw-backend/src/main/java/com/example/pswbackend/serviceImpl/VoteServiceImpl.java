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

            Doctor d1=doctorRepository.findOneById(Long.parseLong(dto.getForId()));
            int stars=d1.getStars();
            int num=d1.getNum_votes();
            d1.setStars(stars+Integer.valueOf(dto.getRating()));
            d1.setNum_votes(num+1);
            doctorRepository.save(d1);

            return voteDoctor1;

        }else{

            Long lastRate=voteDoctorRepository.findByPatientIdAndDoctorId(Long.parseLong(dto.getPatientId()), Long.parseLong(dto.getForId())).getStars();

            voteDoctor.setStars(Long.parseLong(dto.getRating()));
            voteDoctorRepository.save(voteDoctor);

            Doctor d2=doctorRepository.findOneById(Long.parseLong(dto.getForId()));
            int stars=d2.getStars()-Integer.valueOf(String.valueOf(lastRate));
            d2.setStars(stars+Integer.valueOf(dto.getRating()));
            doctorRepository.save(d2);

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

            Clinic c1=clinicRepository.findOneById(Long.parseLong(dto.getForId()));
            int stars=c1.getStars();
            int num=c1.getNum_votes();
            c1.setStars(stars+Integer.valueOf(dto.getRating()));
            c1.setNum_votes(num+1);
            clinicRepository.save(c1);

            voteClinicRepository.save(voteClinic1);
            return voteClinic1;

        }else{

            Long lastRate=voteClinicRepository.findByPatientIdAndClinicId(Long.parseLong(dto.getPatientId()), Long.parseLong(dto.getForId())).getStars();

            voteClinic.setStars(Long.parseLong(dto.getRating()));
            voteClinicRepository.save(voteClinic);

            Clinic c2=clinicRepository.findOneById(Long.parseLong(dto.getForId()));
            int stars=c2.getStars()-Integer.valueOf(String.valueOf(lastRate));
            c2.setStars(stars+Integer.valueOf(dto.getRating()));
            clinicRepository.save(c2);

            return voteClinic;
        }
    }
}
