package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositories;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

 private final RoleRepositories repositories;

    public RoleServiceImpl(RoleRepositories repositories) {
        this.repositories = repositories;
    }

    @Override
    public List<Role> getAllRoles() {
        return repositories.findAll();
    }

    @Override
    public Role getRoleById(long id) {
        return repositories.getById(id);
    }

    public RoleRepositories getRepositories() {
        return repositories;
    }
}
