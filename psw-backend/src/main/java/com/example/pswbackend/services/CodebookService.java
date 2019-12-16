package com.example.pswbackend.services;

import com.example.pswbackend.domain.Diagnosis;
import com.example.pswbackend.domain.Drug;
import com.example.pswbackend.dto.DiagnosisDTO;
import com.example.pswbackend.dto.DrugDTO;

public interface CodebookService {

    Drug saveDrug(DrugDTO drugDTO);
    Boolean updateDrug(Drug drug, DrugDTO drugDTO);
    Boolean deleteDrug(Long id);

    Diagnosis saveDiagnosis(DiagnosisDTO diagnosisDTO);
    Boolean updateDiagnosis(Diagnosis diagnosis, DiagnosisDTO diagnosisDTO);
    Boolean deleteDiagnosis(Long id);

}
