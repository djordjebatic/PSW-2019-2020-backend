package com.example.pswbackend.services;

import com.example.pswbackend.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findById(Long id);
    List<Role> findByName(String name);
}
