package com.example.pswbackend.services;

import com.example.pswbackend.domain.ClinicAdmin;
import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.domain.PaidTimeOffNurse;
import com.example.pswbackend.dto.AbsenceDoctorDTO;
import com.example.pswbackend.dto.AbsenceNurseDTO;
import com.example.pswbackend.enums.PaidTimeOffStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public interface AbsenceRequestService {

    List<AbsenceDoctorDTO> getDoctorAbsenceRequests();

    List<AbsenceNurseDTO> getNurseAbsenceRequests();

    boolean acceptDoctorRequest(AbsenceDoctorDTO dto);

    boolean acceptNurseRequest(AbsenceNurseDTO dto);

    boolean denyDoctorRequest(AbsenceDoctorDTO dto);

    boolean denyNurseRequest(AbsenceNurseDTO dto);
}
