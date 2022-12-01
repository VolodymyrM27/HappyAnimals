package com.motrechko.HappyAnimals.dto;

import com.motrechko.HappyAnimals.entity.User;
import lombok.Data;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class AuthenticationDto {
        private final String email;
        private final String password;
}
