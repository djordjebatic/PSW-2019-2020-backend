package com.example.pswbackend.services;

import com.example.pswbackend.domain.Authority;

import java.util.List;

public interface RoleService {

    List<Authority> findById(Long id);
    List<Authority> findByName(String name);
}
