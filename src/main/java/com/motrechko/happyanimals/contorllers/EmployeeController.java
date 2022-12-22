package com.motrechko.happyanimals.contorllers;

import com.motrechko.happyanimals.dto.RegistrationDto;
import com.motrechko.happyanimals.dto.TaskDto;
import com.motrechko.happyanimals.dto.UserDto;
import com.motrechko.happyanimals.entity.User;
import com.motrechko.happyanimals.exception.EmployeeNotFoundException;
import com.motrechko.happyanimals.security.jwt.JwtMapper;
import com.motrechko.happyanimals.service.impl.UserServiceImpl;
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
    @PostMapping(path = "/employees")
    public UserDto newEmployee(@RequestBody RegistrationDto employee){
        return UserDto.fromUser(
                userService.register(RegistrationDto.fromDtoToUser(employee), employee.isEmployee())
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees/{id}")
    public UserDto one(@PathVariable Long id) {
        return userService.findById(id)
                .map(UserDto::fromUser)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/employees/me")
    public UserDto getCurrentUser() {
        return userService.findById(JwtMapper.getId())
                .map(UserDto::fromUser)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employees/{id}")
    public User replaceEmployee(@RequestBody User newEmployee, @PathVariable Long id) {
        return userService.replaceOrAdd(newEmployee, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        userService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/employees/{id}/task", consumes = "application/json")
    public TaskDto addNewTask(@RequestBody TaskDto task, @PathVariable Long id) {
        return TaskDto.fromTask(
                userService.addTask(
                        TaskDto.fromDto(task), id)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/employees/{id}/task")
    public List<TaskDto> getAllTasks(@PathVariable Long id) {
        return userService.getAllTasks(id)
                .stream()
                .map(TaskDto::fromTask)
                .collect(Collectors.toList());
    }
}

