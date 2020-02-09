package com.example.pswbackend.serviceImpl;

import com.example.pswbackend.domain.Diagnosis;
import com.example.pswbackend.domain.Drug;
import com.example.pswbackend.dto.DiagnosisDTO;
import com.example.pswbackend.dto.DrugDTO;
import com.example.pswbackend.repositories.DiagnosisRepository;
import com.example.pswbackend.repositories.DrugRepository;
import com.example.pswbackend.services.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@Transactional(readOnly = true)
public class CodebookServiceImpl implements CodebookService {

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Autowired
    DrugRepository drugRepository;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Drug saveDrug(DrugDTO drugDTO) {

        Drug newDrug = new Drug(drugDTO.getName(), drugDTO.getIngredient(), drugDTO.getDescription());
        drugRepository.save(newDrug);

        return newDrug;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Drug updateDrug(Drug drug, DrugDTO drugDTO) throws IllegalTransactionStateException {

        if (drug == null || drugDTO == null){
            return null;
        }
        drug.setName(drugDTO.getName());
        drug.setDescription(drugDTO.getDescription());
        drug.setIngredient(drugDTO.getIngredient());

        return drugRepository.save(drug);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean deleteDrug(Long id){
        try{
            drugRepository.deleteById(id);
        }
        catch (EntityNotFoundException e){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Diagnosis saveDiagnosis(DiagnosisDTO diagnosisDTO) {

        Diagnosis newDiagnosis = new Diagnosis(diagnosisDTO.getName(), diagnosisDTO.getDescription());
        diagnosisRepository.save(newDiagnosis);

        return newDiagnosis;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean updateDiagnosis(Diagnosis diagnosis, DiagnosisDTO diagnosisDTO) {

        try {
            diagnosis.setName(diagnosisDTO.getName());
            diagnosis.setDescription(diagnosisDTO.getDescription());

            diagnosisRepository.save(diagnosis);
        }
        catch(EntityNotFoundException e){
            return false;
        }

        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean deleteDiagnosis(Long id){
        try {
            diagnosisRepository.deleteById(id);
        }
        catch (EntityNotFoundException e){
            return false;
        }
        return true;
    }

}
