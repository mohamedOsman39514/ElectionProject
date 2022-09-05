package com.example.elections.rest.mapper;


import com.example.elections.model.Role;
import com.example.elections.rest.dtos.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RoleDto> toRoleDTOs(List<Role> roles);

    Role toRole(RoleDto roleDTO);

    RoleDto toRoleDTO(Role role);
}
