package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.ExaminationReport;
import com.example.pswbackend.dto.ExaminationReportDTO;

public interface ExaminationReportService {

    ExaminationReportDTO create(Appointment appointment, Doctor doctor, ExaminationReportDTO examinationReportDTO);

    ExaminationReportDTO edit(long examinationReportId, ExaminationReportDTO examinationReportDTO);

    ExaminationReport getExaminationReport(Long id);
}
