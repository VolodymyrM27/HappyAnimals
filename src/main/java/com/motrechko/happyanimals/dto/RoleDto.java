package com.motrechko.happyanimals.dto;

import com.motrechko.happyanimals.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.motrechko.happyanimals.entity.Role} entity
 */
@Data
public class RoleDto  {
    private final String name;

    public static List<RoleDto> fromRole(List<Role> role){
        List<RoleDto> roleDtos = new ArrayList<>();
        role.forEach(role1 -> roleDtos.add(new RoleDto(role1.getName())));
        return roleDtos;
    }
}