package com.example.pswbackend.services;

import com.example.pswbackend.domain.Appointment;
import com.example.pswbackend.domain.Doctor;
import com.example.pswbackend.domain.PaidTimeOffDoctor;
import com.example.pswbackend.dto.*;

import java.time.LocalTime;
import java.util.List;

public interface DoctorService {

    boolean scheduleAppointment(AppointmentDoctorDTO dto);

    Doctor findById(long id);
    List<Doctor> findByClinicId(long id);

    Doctor addNew(NewDoctorDTO dto);

    List<Doctor> findAll();
    List<NewDoctorDTO> findByWorkingTime(LocalTime start, LocalTime end);

    Doctor getLoggedInDoctor();
    List<ResultDoctorDTO> filterDoctors(FilterDoctorsDTO dto);

    List<NewDoctorDTO> findClinicDoctors(Long clinicId);

    PaidTimeOffDoctor requestLeave(Long id, PaidTimeOffDoctorDTO dto);

    boolean alreadyRequestedLeave(Doctor dr);
    boolean alreadyOnLeave(Doctor dr);

    Boolean deleteOneById(Long id);
    List<Doctor> findByClinicIdAndSpecializationId(Appointment appointment);

    List<NewDoctorDTO> getAvailableDoctorsByDateAndTime(DateAndTimeDTO dto);

    List<Doctor> getDocsBySpecialization(Long id);

}
