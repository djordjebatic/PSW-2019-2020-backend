package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public boolean scheduleAppointment(AppointmentDoctorDTO dto) {

        Patient patient = patientRepo.findById(Long.parseLong(dto.getPatient())).get();
        Doctor doctor = doctorRepo.findById(Long.parseLong(dto.getDoctor())).get();

        String date = dto.getDate();
        date = date.substring(8,10) + "." + date.substring(5,7) + "." + date.substring(0,4) + ".";

        String message = "Hello, " + "Clinic Admin" +  // TODO dodati koji admin
                ".\nYou got a new request for scheduling an appointment.\n\nAppointment information: \n" +
                "-----------------------------------\n" +
                "     Patient: " + patient.getFirstName() + " " + patient.getLastName() + "\n" +
                "     Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + "\n" +
                "     Date: " + date +
                "\n     Time: " + dto.getTime() +
                "\n-----------------------------------";

        clinicAdminService.receiveAppointmentRequest(dto);

        emailService.sendEmail("jelenadostic2@gmail.com", "Scheduling Appointment", message);

        return true; //za sad uvek true
    }

    @Override
    public Doctor findById(long id) {
        return doctorRepo.findById(id).get();
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }

    @Override
    public List<Doctor> findByClinicId(long id) { return doctorRepo.findByClinicId(id);
    }

    @Override
    public Doctor getLoggedInDoctor() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Account account = accountRepository.findByEmail(currentUser.getName());
            Doctor doctor = (Doctor)account;
            if (doctor != null) {
                return doctor;
            }
        } catch (UsernameNotFoundException ex) {
            return null;
        }
        return null;
    }
}
