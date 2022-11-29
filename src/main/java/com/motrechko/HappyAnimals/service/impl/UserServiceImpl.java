package com.motrechko.HappyAnimals.service.impl;

import com.motrechko.HappyAnimals.entity.Role;
import com.motrechko.HappyAnimals.entity.User;
import com.motrechko.HappyAnimals.repository.RoleRepository;
import com.motrechko.HappyAnimals.repository.UserRepository;
import com.motrechko.HappyAnimals.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} user found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);
        log.info("IN findByEmail - user: {} found by username: {}", result,email);
        return null;
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()){
            log.info("IN findByEmail - user: {} found by id: {}", result,id);
            return result.get();
        } else {
            log.info("IN findByEmail - user: {} NOT found by id: {}", result,id);
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with ID: {} was deleted", id);
    }
}
