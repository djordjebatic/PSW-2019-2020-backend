package com.example.pswbackend.controllers;

import com.example.pswbackend.domain.VoteClinic;
import com.example.pswbackend.domain.VoteDoctor;
import com.example.pswbackend.dto.VoteDTO;
import com.example.pswbackend.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class VoteController {

    @Autowired
    VoteService voteService;

    @PutMapping(value="/voteDoctor")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<VoteDoctor> voteDoctor(@RequestBody VoteDTO dto){

        VoteDoctor voteDoctor= voteService.voteForDoctor(dto);

        if(voteDoctor!=null) {
            return new ResponseEntity<>(voteDoctor,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value="/voteClinic")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<VoteClinic> voteClinic(@RequestBody VoteDTO dto){

        VoteClinic voteClinic= voteService.voteForClinic(dto);

        if(voteClinic!=null) {
            return new ResponseEntity<>(voteClinic,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
