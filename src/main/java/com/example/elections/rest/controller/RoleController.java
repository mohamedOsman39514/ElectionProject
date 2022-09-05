package com.example.elections.rest.controller;

import com.example.elections.model.Role;
import com.example.elections.rest.dtos.RoleDto;
import com.example.elections.rest.exception.ResourceNotFound;
import com.example.elections.rest.mapper.RoleMapper;
import com.example.elections.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleDTOS = roleMapper.toRoleDTOs(roleService.findAll());
        return ResponseEntity.ok(roleDTOS);
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleDTO) {
        Role role = roleMapper.toRole(roleDTO);
        roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(value = "id") Long id)
            throws ResourceNotFound {
        Optional<Role> role = roleService.findById(id);
        RoleDto roleDTO = roleMapper.toRoleDTO(role.get());
        return ResponseEntity.ok(roleDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable Long id,	@RequestBody RoleDto roleDTO) throws ResourceNotFound {
        Role role = roleMapper.toRole(roleDTO);
        Role roleId = roleService.findById(id)
                .orElseThrow(()->new ResourceNotFound("Role Not Found"));
        role.setId(id);
        role.setName(role.getName()!=null ? role.getName() : roleId.getName());
        roleService.save(role);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(roleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        roleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
