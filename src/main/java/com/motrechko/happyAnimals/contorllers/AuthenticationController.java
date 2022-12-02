package com.motrechko.happyAnimals.contorllers;

import com.motrechko.happyAnimals.dto.AuthenticationDto;
import com.motrechko.happyAnimals.dto.RegistrationDto;
import com.motrechko.happyAnimals.entity.User;
import com.motrechko.happyAnimals.exception.EmailExistException;
import com.motrechko.happyAnimals.security.jwt.JwtTokenProvider;
import com.motrechko.happyAnimals.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object,Object>> login(@RequestBody AuthenticationDto requestDto) {
        try {
            String username = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByEmail(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("reg")
    public ResponseEntity<String> registration (@RequestBody RegistrationDto newUser){
        if(userService.existByEmail(newUser.getEmail()))
            throw new EmailExistException(newUser.getEmail());
        userService.register(RegistrationDto.fromDtoToUser(newUser));
        return ResponseEntity.ok("New user succsefully registered");
    }
}
