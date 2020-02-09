package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Authority;
import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.PatientDTO;
import com.example.pswbackend.dto.RegisterApprovalDTO;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.repositories.AppointmentRepository;
import com.example.pswbackend.repositories.PatientRepository;
import com.example.pswbackend.services.AccountService;
import com.example.pswbackend.services.EmailService;
import com.example.pswbackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    AccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<RegisterApprovalDTO> findByStatus(Status patientStatus) {
        List<Patient> patients = patientRepository.findByStatus(patientStatus);
        List<RegisterApprovalDTO> patientsDTO = new ArrayList<>();
        for (Patient p : patients){
           patientsDTO.add(convertToDTO(p));
        }

        return patientsDTO;
    }

    //TODO Use ModelMapper dependency
    private RegisterApprovalDTO convertToDTO(Patient p) {
        RegisterApprovalDTO regApproveDTO = new RegisterApprovalDTO();
        regApproveDTO.setId(p.getId());
        regApproveDTO.setFirstName(p.getFirstName());
        regApproveDTO.setLastName(p.getLastName());
        regApproveDTO.setEmail(p.getUsername());
        regApproveDTO.setCity(p.getCity());
        regApproveDTO.setCountry(p.getCountry());

        return regApproveDTO;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Patient approveRegistration(Long id) {

        Patient patient = patientRepository.findById(id).orElseGet(null);
        if (patient == null) {
            return null;
        }
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(patient);
        patient.setPatientStatus(Status.APPROVED);
        patient.setMedicalRecord(medicalRecord);

        return patientRepository.save(patient);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean rejectRegistration(Long id, String message) {
        Patient patient = patientRepository.findById(id).orElseGet(null);

        if (patient == null || patient.getPatientStatus().equals(Status.APPROVED)){
            return false;
        }

        String s = "Dear " + patient.getFirstName() + " " + patient.getLastName() +
                ", unfortunately your registration request has been rejected by the Clinic Center Administrator for the following reason: ";
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(message);
        String ret= sb.toString();

        emailService.sendEmail(patient.getUsername(), "Registration Request Response", ret);

        patientRepository.deleteById(id);

        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Patient registerPatient(PatientDTO patientDTO) {
        UserDetails userDetails = accountService.findByEmail(patientDTO.getEmail());
        if (userDetails != null) {
            return null;
        }

        if (patientRepository.findByEmail(patientDTO.getEmail()) != null) {
            return null;
        }

        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setAddress(patientDTO.getAddress());
        patient.setCity(patientDTO.getCity());
        patient.setCountry(patientDTO.getCountry());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setEmail(patientDTO.getEmail());
        patient.setPassword(passwordEncoder.encode(patientDTO.getPassword()));
        patient.setMedicalNumber(patientDTO.getMedicalNumber());

        patient.setPatientStatus(Status.AWAITING_APPROVAL);

        List<Authority> authorities = new ArrayList<>();

        Authority a = new Authority();
        a.setName("ROLE_PATIENT");
        a.setId(new Long(1));
        authorities.add(a);
        patient.setAuthorities(authorities);

        return patientRepository.save(patient);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean sendVerificationEmail(Long id) {
        Patient patient = patientRepository.findOneById(id);

        if (patient == null) {
            return false;
        }

        patient.setPatientStatus(Status.AWAITING_VERIFICATON);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Registration request has been successful. Please visit this link in order to verify your email address. ");
        stringBuilder.append("https://psw-isa-tim3-frontend.herokuapp.com/verify/");
        stringBuilder.append(id);
        stringBuilder.append(".");

        String s = stringBuilder.toString();
        emailService.sendEmail(patient.getUsername(), "Registration Request Response", s);

        patientRepository.save(patient);

        return true;
    }

    @Override
    public List<Patient> findByClinicId(Long id) {

        List<Appointment> appointments = appointmentRepository.findByClinicId(id);

        for (Appointment appointment : appointments){

        }

        return null;
    }
}
