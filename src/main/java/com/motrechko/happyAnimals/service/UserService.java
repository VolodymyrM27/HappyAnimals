package com.motrechko.happyAnimals.service;

import com.motrechko.happyAnimals.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByEmail(String email);
    Optional<User> findById(Long id);
    void delete(Long id);

    User replaceOrAdd(User user, Long id);

    boolean existByEmail(String email);

}
