package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.Clinic;
import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.dto.*;
import com.example.pswbackend.repositories.ClinicAdminRepository;
import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.ClinicDTO;
import com.example.pswbackend.repositories.AppointmentPriceRepository;
import com.example.pswbackend.repositories.ClinicRepository;
import com.example.pswbackend.services.AppointmentService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.repositories.DoctorRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
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
    ClinicAdminRepository clinicAdminRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentPriceRepository appointmentPriceRepository;

    @Override
    public ClinicDTO findById(Long id) {
        Clinic clinic = clinicRepository.findOneById(id);
        if (clinic == null) {
            return null;
        }

        return new ClinicDTO(clinic.getId(), clinic.getName(), clinic.getDescription(), clinic.getAddress(), clinic.getCity(), clinic.getStars());
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
    public List<Clinic> findByNameIgnoreCase(String name) {
        return clinicRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Clinic register(ClinicDTO clinicDTO) {

        List<Clinic> clinics = findByNameIgnoreCase(clinicDTO.getName());
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

    @Override
    public List<ResultClinicDTO> filterClinics(FilterClinicsDTO dto) {

        List<Clinic> clinicList = new ArrayList<Clinic>();
        List<Clinic> clinicListAll = clinicRepository.findAll();
        for(Clinic c: clinicListAll){
             List<Doctor> clinicDoctors = doctorRepository.findByClinicId(c.getId());
             outerLoop:
             for(Doctor d : clinicDoctors){

                if(d.getSpecialization().getId().toString().equals(dto.getType())){

                    long duration = Duration.between(dto.getDate().atStartOfDay().plusHours(8), dto.getDate().atStartOfDay().plusHours(8).plusMinutes(45)).toMillis() / 1000;

                    LocalDateTime start = dto.getDate().atStartOfDay().plusHours(8);
                    LocalDateTime end = start.plusSeconds(duration);
                    LocalDateTime end_search = dto.getDate().atStartOfDay().plusHours(20);

                    for(int i=0; i<16; i++){
                        LocalDateTime st=start.plusSeconds(i*duration);

                        if (isDoctorAvailable(d, st, st.plusSeconds(duration))) {
                            clinicList.add(clinicRepository.findOneById(d.getClinic().getId()));
                            break outerLoop;
                        }
                    }
                }
             }
        }

        List<ResultClinicDTO> resultList= new ArrayList<>();
        for(Clinic c : clinicList) {

            List<AppointmentPrice> a=appointmentPriceRepository.findByAppointmentTypeId(Long.parseLong(dto.getType()));
            AppointmentPrice ap=a.get(1);

            ResultClinicDTO resultDTO = new ResultClinicDTO(c.getId().toString(), c.getName(), c.getDescription(), c.getAddress(), c.getCity(), Integer.toString(c.getStars()), Integer.toString(c.getNum_votes()), String.valueOf(ap.getPrice()) );
            resultList.add(resultDTO);
        }
        return resultList;
    }

    public boolean isDoctorAvailable(Doctor doctor, LocalDateTime start, LocalDateTime end) {
        List<Appointment> appointments = appointmentService.getDoctorAppointmentsDuringTheDay(doctor.getId(), start);
        boolean available = false;
        if (appointments.isEmpty()){
            return true;
        }
        else{
            for (Appointment appointment : appointments) {
                if (!checkTaken(appointment, start, end)) {
                    available = true;
                }
            }
        }
        return available;
    }

    public List<Doctor> getAvailableDoctors(Appointment appointment){
        List<Doctor> doctors = doctorRepository.findByClinicId(appointment.getClinic().getId());
        List<Doctor> availableDoctors = new ArrayList<>();
        for (Doctor d : doctors){
            if (isDoctorAvailable(d, appointment.getStartDateTime(), appointment.getEndDateTime())){
                availableDoctors.add(d);
            }
        }
        return availableDoctors;
    }

    public boolean checkTaken(Appointment appointment, LocalDateTime start, LocalDateTime end){

        LocalDateTime appointment_start = appointment.getStartDateTime();
        LocalDateTime appointment_end = appointment.getEndDateTime();

        if (appointment_end.isAfter(end)){
            if (appointment_start.isBefore(end)){
                return true;
            }
        }
        if (appointment_start.isBefore(start)){
            if (appointment_end.isAfter(start)){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean updateClinic(Clinic c, ClinicDTO dto) {

        try{
            c.setName(dto.getName());
            c.setDescription(dto.getDescription());
            c.setCity(dto.getCity());
            c.setAddress(dto.getAddress());
            clinicRepository.save(c);
        }
        catch(EntityNotFoundException e){
            return false;
        }

        return true;
    }
}
