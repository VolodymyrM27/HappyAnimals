package com.motrechko.happyanimals.service.impl;

import com.motrechko.happyanimals.entity.Role;
import com.motrechko.happyanimals.entity.Task;
import com.motrechko.happyanimals.entity.User;
import com.motrechko.happyanimals.exception.EmployeeNotFoundException;
import com.motrechko.happyanimals.exception.PermissionDeniedException;
import com.motrechko.happyanimals.repository.RoleRepository;
import com.motrechko.happyanimals.repository.TaskRepository;
import com.motrechko.happyanimals.repository.UserRepository;
import com.motrechko.happyanimals.security.jwt.JwtMapper;
import com.motrechko.happyanimals.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TaskRepository taskRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, TaskRepository taskRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.taskRepository = taskRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user, boolean isEmployee) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        if (!isEmployee) {
            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            userRoles.add(roleAdmin);
        } else {
            userRepository.saveEmployee(JwtMapper.getId(), user.getId());
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        Long userId = JwtMapper.getId();
        List<User> result = userRepository.findEmployees(userId);
        log.info("IN getAll - {} user found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);
        log.info("IN findByEmail - user: {} found by username: {}", result,email);
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            if (isPermitted(result.get()) || id.equals(JwtMapper.getId())) {
                log.info("IN findByEmail - user: {} found by id: {}", result, id);
            } else {
                log.info("IN findByEmail - user: {} NOT found by id: {}", result, id);
                throw new PermissionDeniedException("find user with id " + id);
            }
        }
        return result;
    }

    private boolean isPermitted(User user) {
        return userRepository.existsByEmployeesAllIgnoreCase(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with ID: {} was deleted", id);
    }

    @Override
    public User replaceOrAdd(User user, Long id) {
        return userRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(user.getFirstName());
                    employee.setLastName(user.getLastName());
                    employee.setEmail(user.getEmail());
                    employee.setPassword(user.getPassword());
                    return userRepository.save(employee);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Task addTask(Task task, Long employeeId) {
        Optional<User> employee = findById(employeeId);
        if (employee.isPresent()) {
            task.setUser(employee.get());
            return taskRepository.save(task);
        } else {
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public List<Task> getAllTasks(Long employeeId) {
        return taskRepository.findByUser_Id(employeeId);
    }
}
