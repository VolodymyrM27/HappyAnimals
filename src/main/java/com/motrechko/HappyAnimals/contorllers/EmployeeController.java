package com.motrechko.HappyAnimals.contorllers;

import com.motrechko.HappyAnimals.entity.User;
import com.motrechko.HappyAnimals.repository.UserRepository;
import com.motrechko.HappyAnimals.exception.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final UserRepository repository;


    EmployeeController(UserRepository repository){
        this.repository = repository;

    }

    @GetMapping("/employees")
    public List<User> all() {
        return repository.findAll();
    }

    @PostMapping(path = "/employees", consumes="application/json")
    public User newEmployee(@RequestBody User employee){
        return repository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public User one(@PathVariable Long id) {

        return repository.findById(id)
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

