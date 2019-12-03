package com.example.pswbackend.services;

import com.example.pswbackend.domain.Role;
import com.example.pswbackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findById(Long id) {
        Role auth = this.roleRepository.getOne(id);
        List<Role> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Role> findByName(String name) {
        Role auth = this.roleRepository.findByName(name);
        List<Role> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }
}
