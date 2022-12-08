package com.motrechko.happyanimals.dto;

import com.motrechko.happyanimals.entity.User;
import lombok.Data;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class AuthenticationDto {
        private final String email;
        private final String password;
}
