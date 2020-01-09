package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Account;
import com.example.pswbackend.domain.Nurse;
import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.dto.PrescriptionDTO;
import com.example.pswbackend.enums.PrescriptionEnum;
import com.example.pswbackend.repositories.AccountRepository;
import com.example.pswbackend.repositories.PrescriptionRepository;
import com.example.pswbackend.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Override
    public Nurse getLoggedInNurse() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            Account account = accountRepository.findByEmail(currentUser.getName());
            Nurse nurse = (Nurse)account;
            if (nurse != null) {
                return nurse;
            }
        } catch (UsernameNotFoundException ex) {
            return null;
        }
        return null;
    }

    @Override
    public PrescriptionDTO authenticatePrescription(Long nurseId, Long prescriptionId) {

        Prescription prescription = prescriptionRepository.findOneById(prescriptionId);
        if (prescription == null){
            return null;
        }

        prescription.setPrescriptionEnum(PrescriptionEnum.AUTHENTICATED);

        return new PrescriptionDTO(prescriptionRepository.save(prescription));
    }

}
