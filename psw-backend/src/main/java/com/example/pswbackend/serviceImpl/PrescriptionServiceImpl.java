package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.domain.Prescription;
import com.example.pswbackend.dto.PrescriptionDTO;
import com.example.pswbackend.enums.PrescriptionEnum;
import com.example.pswbackend.repositories.PrescriptionRepository;
import com.example.pswbackend.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Override
    public List<PrescriptionDTO> getByNurseId(Long id) {
        return convertToDTO(prescriptionRepository.findByNurseIdAndPrescriptionEnumNot(id, PrescriptionEnum.AUTHENTICATED));
    }

    public List<PrescriptionDTO> convertToDTO(List<Prescription> prescriptions){

        if (prescriptions.isEmpty()) {
            return new ArrayList<>();
        }
        List<PrescriptionDTO> prescriptionDTOS = new ArrayList<>();
        for (Prescription prescription : prescriptions) {
            prescriptionDTOS.add(new PrescriptionDTO(prescription));
        }
        return prescriptionDTOS;
    }

    @Override
    public List<String> getPrescriptionsPatient(ExaminationReport e) {

        List<String> prescriptions = new ArrayList<>();

        for (Prescription p : e.getPrescriptions()) {
            String s = p.getDrug().getName();

            if (e.getPrescriptions().toArray().length == (prescriptions.size() + 1)) {
                prescriptions.add(s);
            } else {
                prescriptions.add(s + ", ");
            }
        }
        return prescriptions;
    }

}
