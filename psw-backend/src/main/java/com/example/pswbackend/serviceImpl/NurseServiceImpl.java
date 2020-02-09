package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.*;
import com.example.pswbackend.dto.PaidTimeOffNurseDTO;
import com.example.pswbackend.dto.PrescriptionDTO;
import com.example.pswbackend.enums.AppointmentStatus;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.enums.PaidTimeOffType;
import com.example.pswbackend.enums.PrescriptionEnum;
import com.example.pswbackend.repositories.*;
import com.example.pswbackend.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class NurseServiceImpl implements NurseService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    NurseRepository nurseRepository;

    @Autowired
    PaidTimeOffNurseRepository paidTimeOffNurseRepository;

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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public PrescriptionDTO authenticatePrescription(Long nurseId, Long prescriptionId) {

        Prescription prescription = prescriptionRepository.findOneById(prescriptionId);
        if (prescription == null){
            return null;
        }

        prescription.setPrescriptionEnum(PrescriptionEnum.AUTHENTICATED);

        return new PrescriptionDTO(prescriptionRepository.save(prescription));
    }

    @Override
    public PaidTimeOffNurse requestLeave(Long id, PaidTimeOffNurseDTO paidTimeOffNurseDTO) {

        Nurse nurse = nurseRepository.findOneById(id);

        List<AppointmentStatus> statuses = new ArrayList<>();
        statuses.add(AppointmentStatus.APPROVED);
        statuses.add(AppointmentStatus.PREDEF_BOOKED);

        List<Appointment> appointmens = appointmentRepository
                .findByNurseIdAndStartDateTimeGreaterThanEqualAndEndDateTimeLessThanAndStatusIn(id,
                        paidTimeOffNurseDTO.getStartDateTime(), paidTimeOffNurseDTO.getEndDateTime(),
                        statuses);

        if (!appointmens.isEmpty()){
            return null;
        }

        PaidTimeOffNurse paidTimeOffNurse = new PaidTimeOffNurse();

        paidTimeOffNurse.setNurse(nurse);
        paidTimeOffNurse.setComment(paidTimeOffNurseDTO.getComment());
        paidTimeOffNurse.setStartDateTime(paidTimeOffNurseDTO.getStartDateTime());
        paidTimeOffNurse.setEndDateTime(paidTimeOffNurseDTO.getEndDateTime());

        if (paidTimeOffNurseDTO.getPaidTimeOffType().equals(PaidTimeOffType.ANNUAL_LEAVE)){
            paidTimeOffNurse.setPaidTimeOffType(PaidTimeOffType.ANNUAL_LEAVE);
        }
        else {
            paidTimeOffNurse.setPaidTimeOffType(PaidTimeOffType.SICK_LEAVE);
        }

        paidTimeOffNurse.setPaidTimeOffStatus(PaidTimeOffStatus.REQUESTED);

        //nurse.setPaidTimeOffNurse(paidTimeOffNurse);

        //nurseRepository.save(nurse);

        return paidTimeOffNurseRepository.save(paidTimeOffNurse);
    }

    @Override
    public boolean alreadyRequestedLeave(Nurse nurse) {
        if (nurse.getPaidTimeOffNurse() != null) {
            if (nurse.getPaidTimeOffNurse().getPaidTimeOffStatus().equals(PaidTimeOffStatus.REQUESTED)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean alreadyOnLeave(Nurse nurse) {

        if (nurse.getPaidTimeOffNurse() != null) {
            if (nurse.getPaidTimeOffNurse().getPaidTimeOffStatus().equals(PaidTimeOffStatus.APPROVED)) {
                if (nurse.getPaidTimeOffNurse().getStartDateTime().isAfter(LocalDateTime.now()) &&
                        nurse.getPaidTimeOffNurse().getEndDateTime().isBefore(LocalDateTime.now())
                )
                    return true;
            }
        }
        return false;
    }

}
