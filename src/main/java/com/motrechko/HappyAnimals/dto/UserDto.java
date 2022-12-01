package com.motrechko.HappyAnimals.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.motrechko.HappyAnimals.entity.Role;
import com.motrechko.HappyAnimals.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.motrechko.HappyAnimals.entity.User} entity
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto  {
    private  Date created;
    private  Date updated;
    private  String email;
    private  String firstName;
    private  String lastName;
    private  List<RoleDto> roles;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setCreated(user.getCreated());
        userDto.setUpdated(user.getUpdated());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(RoleDto.fromRole(user.getRoles()));
        return userDto;
    }
}