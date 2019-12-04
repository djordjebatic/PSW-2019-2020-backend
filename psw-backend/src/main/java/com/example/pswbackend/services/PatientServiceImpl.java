package com.example.pswbackend.services;

import com.example.pswbackend.domain.MedicalRecord;
import com.example.pswbackend.domain.Patient;
import com.example.pswbackend.dto.RegisterApprovalDTO;
import com.example.pswbackend.enums.Status;
import com.example.pswbackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    EmailService emailService;

    @Override
    public List<RegisterApprovalDTO> findByStatus(Status patientStatus) {
        List<Patient> patients = patientRepository.findByStatus(patientStatus);
        List<RegisterApprovalDTO> patientsDTO = new ArrayList<RegisterApprovalDTO>();
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

        return regApproveDTO;
    }

    @Override
    public Patient approveRegistration(Long id) {

        Patient patient = patientRepository.findById(id).orElseGet(null);

        if (patient == null) {
            return null;
        }

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatientId(patient.getId());

        patient.setPatientStatus(Status.APPROVED);
        patient.setMedicalRecord(medicalRecord);

        String s = "Registration request has been approved by the Administrator! You can now log in to the Clinical Centre System";
        emailService.sendEmail(patient.getUsername(), "Registration Request Response", s);

        return patientRepository.save(patient);
    }

    @Override
    public boolean rejectRegistration(Long id, String message) {
        Patient patient = patientRepository.findById(id).orElseGet(null);

        if (patient == null || patient.getPatientStatus().equals(Status.APPROVED)){
            return false;
        }

        String s = "Registration request has been approved by the Administrator! You can now log in to the Clinical Centre System. Reason: ";
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(message);
        String ret= sb.toString();

        emailService.sendEmail(patient.getUsername(), "Registration Request Response", ret);

        patientRepository.deleteById(id);

        return true;
    }
}
