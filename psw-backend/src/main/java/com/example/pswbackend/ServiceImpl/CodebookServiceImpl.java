package com.example.pswbackend.ServiceImpl;

import com.example.pswbackend.domain.Diagnosis;
import com.example.pswbackend.domain.Drug;
import com.example.pswbackend.dto.DiagnosisDTO;
import com.example.pswbackend.dto.DrugDTO;
import com.example.pswbackend.repositories.DiagnosisRepository;
import com.example.pswbackend.repositories.DrugRepository;
import com.example.pswbackend.services.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Transactional(readOnly = false)
    public Drug saveDrug(DrugDTO drugDTO) {

        Drug newDrug = new Drug(drugDTO.getName(), drugDTO.getIngredient(), drugDTO.getDescription());
        drugRepository.save(newDrug);

        return newDrug;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Boolean updateDrug(Drug drug, DrugDTO drugDTO) {

        try{
            drug.setName(drugDTO.getName());
            drug.setDescription(drugDTO.getDescription());
            drug.setIngredient(drugDTO.getIngredient());
            drugRepository.save(drug);
        }
        catch(EntityNotFoundException e){
            return false;
        }

        return true;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void deleteDrug(Drug drug){

        drugRepository.deleteById(drug.getId());
    }

    @Override
    @Transactional(readOnly = false)
    public Diagnosis saveDiagnosis(DiagnosisDTO diagnosisDTO) {

        Diagnosis newDiagnosis = new Diagnosis(diagnosisDTO.getName(), diagnosisDTO.getDescription());
        diagnosisRepository.save(newDiagnosis);

        return newDiagnosis;
    }



}
