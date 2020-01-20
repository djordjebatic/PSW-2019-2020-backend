package com.example.pswbackend.repositories;

import com.example.pswbackend.domain.ExaminationReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExaminationReportRepository extends JpaRepository<ExaminationReport, Long> {

    ExaminationReport findByAppointmentId(Long id);
    List<ExaminationReport> findByMedicalRecordIdOrderByTimeCreated(Long id);
    ExaminationReport findOneById(Long id);
}
