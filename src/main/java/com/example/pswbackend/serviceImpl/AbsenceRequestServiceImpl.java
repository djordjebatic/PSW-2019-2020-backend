package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.domain.PaidTimeOffNurse;
import com.example.pswbackend.dto.AbsenceDoctorDTO;
import com.example.pswbackend.dto.AbsenceNurseDTO;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import com.example.pswbackend.repositories.PaidTimeOffDoctorRepository;
import com.example.pswbackend.repositories.PaidTimeOffNurseRepository;
import com.example.pswbackend.services.AbsenceRequestService;
import com.example.pswbackend.services.ClinicAdminService;
import com.example.pswbackend.services.DoctorService;
import com.example.pswbackend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbsenceRequestServiceImpl implements AbsenceRequestService {

    @Autowired
    private PaidTimeOffDoctorRepository paidTimeOffDoctorRepository;

    @Autowired
    private PaidTimeOffNurseRepository paidTimeOffNurseRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private EmailService emailService;

    @Override
    public List<AbsenceDoctorDTO> getDoctorAbsenceRequests(){

        List<PaidTimeOffDoctor> list = paidTimeOffDoctorRepository.findAll();
        List<AbsenceDoctorDTO> listToReturn = new ArrayList<>();
        ClinicAdmin doc = clinicAdminService.getLoggedInClinicAdmin();

        if (list == null){
            return null;
        }

        if (doc == null){
            return null;
        }

        for (int i=0;i < list.size(); i++){
            if (list.get(i).getDoctor().getClinic().getId() == doc.getClinic().getId()){
                if (list.get(i).getPaidTimeOffStatus() == PaidTimeOffStatus.REQUESTED){
                    listToReturn.add(mapDoctorToDTO(list.get(i)));
                }
            }
        }

        return listToReturn;
    }

    @Override
    public List<AbsenceNurseDTO> getNurseAbsenceRequests(){

        List<PaidTimeOffNurse> list = paidTimeOffNurseRepository.findAll();
        List<AbsenceNurseDTO> listToReturn = new ArrayList<>();
        ClinicAdmin nur = clinicAdminService.getLoggedInClinicAdmin();

        if (list == null){
            return null;
        }

        if (nur == null){
            return null;
        }

        for (int i=0;i < list.size(); i++){
            if (list.get(i).getNurse().getClinic().getId() == nur.getClinic().getId()){
                if (list.get(i).getPaidTimeOffStatus() == PaidTimeOffStatus.REQUESTED){
                    listToReturn.add(mapNurseToDTO(list.get(i)));
                }
            }
        }

        return listToReturn;
    }

    private AbsenceNurseDTO mapNurseToDTO(PaidTimeOffNurse nurse){

        AbsenceNurseDTO dto = new AbsenceNurseDTO();

        dto.setEmail(nurse.getNurse().getUsername());
        dto.setFirstName(nurse.getNurse().getFirstName());
        dto.setLastName(nurse.getNurse().getLastName());
        dto.setComment(nurse.getComment());
        dto.setStartDateTime(nurse.getStartDateTime());
        dto.setEndDateTime(nurse.getEndDateTime());
        dto.setPaidTimeOffType(nurse.getPaidTimeOffType());
        dto.setId(nurse.getId());

        return dto;
    }

    private AbsenceDoctorDTO mapDoctorToDTO(PaidTimeOffDoctor doc){

        AbsenceDoctorDTO dto = new AbsenceDoctorDTO();

        dto.setEmail(doc.getDoctor().getUsername());
        dto.setFirstName(doc.getDoctor().getFirstName());
        dto.setLastName(doc.getDoctor().getLastName());
        dto.setComment(doc.getComment());
        dto.setStartDateTime(doc.getStartDateTime());
        dto.setEndDateTime(doc.getEndDateTime());
        dto.setPaidTimeOffType(doc.getPaidTimeOffType());
        dto.setId(doc.getId());

        return dto;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean acceptDoctorRequest(AbsenceDoctorDTO dto){
        try {
            PaidTimeOffDoctor doc = paidTimeOffDoctorRepository.findById(dto.getId()).get();
            doc.setPaidTimeOffStatus(PaidTimeOffStatus.APPROVED);
            paidTimeOffDoctorRepository.save(doc);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been accepted." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime();
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean acceptNurseRequest(AbsenceNurseDTO dto){
        try {
            PaidTimeOffNurse nur = paidTimeOffNurseRepository.findById(dto.getId()).get();
            nur.setPaidTimeOffStatus(PaidTimeOffStatus.APPROVED);
            paidTimeOffNurseRepository.save(nur);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been accepted." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime();
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean denyDoctorRequest(AbsenceDoctorDTO dto){
        try {
            PaidTimeOffDoctor doc = paidTimeOffDoctorRepository.findById(dto.getId()).get();
            doc.setPaidTimeOffStatus(PaidTimeOffStatus.DENIED);
            paidTimeOffDoctorRepository.save(doc);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been rejected." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime()
                    + "\n\n Reason for rejection: \n '" + dto.getDenialComment() + "'";
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean denyNurseRequest(AbsenceNurseDTO dto){
        try {
            PaidTimeOffNurse nur = paidTimeOffNurseRepository.findById(dto.getId()).get();
            nur.setPaidTimeOffStatus(PaidTimeOffStatus.DENIED);
            paidTimeOffNurseRepository.save(nur);
            String s = "Hello " + dto.getFirstName() + ",\n Your absence request has been rejected." +
                    "\n Request details: \n Start date: " + dto.getStartDateTime() + "\n End date: " + dto.getEndDateTime()
                    + "\n\n Reason for rejection: \n '" + dto.getDenialComment() + "'";
            emailService.sendEmail(dto.getEmail(), "Absence Request Response", s);
            emailService.sendEmail("jelenadostic1@gmail.com", "Absence Request Response", s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
