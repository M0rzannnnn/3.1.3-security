package ru.vinogradov.kataBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vinogradov.kataBoot.model.Role;
import ru.vinogradov.kataBoot.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
