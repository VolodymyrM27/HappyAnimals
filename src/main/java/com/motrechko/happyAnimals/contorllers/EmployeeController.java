package com.motrechko.happyAnimals.contorllers;

import com.motrechko.happyAnimals.dto.RegistrationDto;
import com.motrechko.happyAnimals.dto.UserDto;
import com.motrechko.happyAnimals.entity.User;
import com.motrechko.happyAnimals.exception.EmployeeNotFoundException;
import com.motrechko.happyAnimals.service.impl.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final UserServiceImpl userService;

    public EmployeeController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees")
    public List<UserDto> all() {
        return userService.getAll()
                .stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/employees", consumes="application/json")
    public UserDto newEmployee(@RequestBody RegistrationDto employee){
        return UserDto.fromUser(
                userService.register(RegistrationDto.fromDtoToUser(employee))
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees/{id}")
    public UserDto one(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserDto::fromUser)
                .orElseThrow( () -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public User replaceEmployee(@RequestBody User newEmployee, @PathVariable Long id) {
            return userService.replaceOrAdd(newEmployee,id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        userService.delete(id);
    }
}

