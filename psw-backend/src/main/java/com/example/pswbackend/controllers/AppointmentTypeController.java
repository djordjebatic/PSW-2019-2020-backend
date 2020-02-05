package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.AppointmentType;
import com.example.pswbackend.domain.Ordination;
import com.example.pswbackend.dto.AppointmentTypeDTO;
import com.example.pswbackend.dto.NewOrdinationDTO;
import com.example.pswbackend.repositories.AppointmentTypeRepository;
import com.example.pswbackend.services.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class AppointmentTypeController {

    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    AppointmentTypeService appointmentTypeService;

    @GetMapping(value="/types", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<AppointmentTypeDTO>> getAllAppTypes(){

        List<AppointmentTypeDTO> appTypeAll= appointmentTypeService.findAll();

        if(appTypeAll==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appTypeAll, HttpStatus.OK);
    }

    @GetMapping(value="/types/{clinicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<List<AppointmentTypeDTO>> getAllAppTypesFromClinic(@PathVariable Long clinicId){

        List<AppointmentTypeDTO> appTypeAll= appointmentTypeService.findByClinicId(clinicId);

        if(appTypeAll==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appTypeAll, HttpStatus.OK);
    }

    @GetMapping(value="/type/{typeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<AppointmentType> getById(@PathVariable Long typeId){

        AppointmentType type = appointmentTypeRepository.findById(typeId).get(); /// dodati i u servis metodu


        if(type==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @PostMapping(value="/types/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<AppointmentType> addNew(@RequestBody AppointmentTypeDTO dto) {

        return new ResponseEntity<AppointmentType>(appointmentTypeService.addNew(dto), HttpStatus.CREATED);
    }

    @PutMapping(value="/type/{typeId}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> updateAppType(@PathVariable Long typeId, @RequestBody AppointmentTypeDTO dto){

        AppointmentType type = appointmentTypeRepository.findOneById(typeId);

        if (type == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Boolean success = appointmentTypeService.updateType(type, dto);

        if(success) {
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/app-type/delete/{id}")
    @PreAuthorize("hasRole('CLINIC_ADMIN')")
    public ResponseEntity<Boolean> deleteOrd(@PathVariable Long id) {

        Boolean deleted = appointmentTypeService.deleteAppType(id);

        if (deleted) {
            return new ResponseEntity<Boolean>(appointmentTypeService.deleteAppType(id), HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(appointmentTypeService.deleteAppType(id), HttpStatus.NOT_FOUND);
    }
}
