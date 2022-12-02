package com.motrechko.happyAnimals.dto;

import com.motrechko.happyAnimals.entity.User;
import lombok.Data;



@Data
public class RegistrationDto {
    private  String email;
    private String password;
    private  String firstName;
    private  String lastName;

    public static User fromDtoToUser(RegistrationDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
