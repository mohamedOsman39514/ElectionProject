package com.example.elections.service;

import com.example.elections.model.Role;
import com.example.elections.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id)
    {
        return roleRepository.findById(id);
    }

    public Role save(Role role)
    {
        return roleRepository.save(role);
    }

    public void deleteById(Long id)
    {
        roleRepository.deleteById(id);
    }
}
