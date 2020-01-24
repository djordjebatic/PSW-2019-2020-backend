package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.AppointmentDoctorDTO;
import com.example.pswbackend.dto.NewDoctorDTO;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.DoctorRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.ClinicService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean scheduleAppointment(AppointmentDoctorDTO dto) {

        Patient patient = patientRepo.findById(dto.getPatient());
        Doctor doctor = doctorRepo.findById(Long.parseLong(dto.getDoctor())).get();

        int type = Integer.parseInt(dto.getType());
        String stringType = type == 0 ? "Examination" : "Operation";

        // TODO dodati startDateTime endDateTime

        String message = "Hello, " + "Clinic Admin" +
                ".\nYou got a new request for scheduling an appointment.\n\nAppointment information: \n" +
                "-----------------------------------\n" +
                "     Doctor: " + doctor.getFirstName() + " " + doctor.getLastName() + "\n" +
                "     Patient: " + patient.getFirstName() + " " + patient.getLastName() + "\n" +
                "     Type: " + stringType + "\n" +
                "     Start: " + dto.getStartDateTime()+
                "\n     End: " + dto.getEndDateTime() +
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
    public List<Doctor> findClinicDoctors(Long id) {
        return doctorRepo.findByClinicId(id);
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

    @Override
    public Doctor addNew(NewDoctorDTO dto){

        Clinic c = clinicService.findClinicById(dto.getClinicId());

        Doctor d = new Doctor();
        d.setFirstName(dto.getFirstName());
        d.setLastName(dto.getLastName());
        d.setEmail(dto.getUsername());
        d.setPassword(passwordEncoder.encode("admin"));
        d.setPhoneNumber(dto.getPhoneNumber());
        d.setCity(dto.getCity());
        d.setCountry(dto.getCountry());
        d.setAddress(dto.getAddress());
        d.setClinic(c);

        List<Authority> authorities = new ArrayList<Authority>();
        Authority a = new Authority();
        a.setName("ROLE_DOCTOR");
        a.setId(2L);
        authorities.add(a);
        d.setAuthorities(authorities);

        c.getDoctors().add(d);
        doctorRepo.save(d);

        return d;
    }
}
