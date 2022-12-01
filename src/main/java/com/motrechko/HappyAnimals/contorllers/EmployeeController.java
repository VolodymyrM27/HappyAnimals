package com.motrechko.HappyAnimals.contorllers;

import com.motrechko.HappyAnimals.dto.UserDto;
import com.motrechko.HappyAnimals.entity.User;
import com.motrechko.HappyAnimals.repository.UserRepository;
import com.motrechko.HappyAnimals.exception.EmployeeNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final UserRepository repository;


    EmployeeController(UserRepository repository){
        this.repository = repository;

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employees")
    public List<UserDto> all() {

        return repository.findAll().stream().map(UserDto::fromUser).collect(Collectors.toList());
    }

    @PostMapping(path = "/employees", consumes="application/json")
    public User newEmployee(@RequestBody User employee){
        return repository.save(employee);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees/{id}")
    public UserDto one(@PathVariable Long id) {

        return repository.findById(id)
                .map(UserDto::fromUser)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public User replaceEmployee(@RequestBody User newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                   // employee.setRole(newEmployee.getRole());
                    employee.setEmail(newEmployee.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

