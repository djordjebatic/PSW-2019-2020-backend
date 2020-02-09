package com.example.pswbackend.services;

import com.example.pswbackend.domain.VoteClinic;
import com.example.pswbackend.domain.VoteDoctor;
import com.example.pswbackend.dto.VoteDTO;

public interface VoteService {

    VoteDoctor voteForDoctor(VoteDTO dto);
    VoteClinic voteForClinic(VoteDTO dto);
}
