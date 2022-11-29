package com.motrechko.HappyAnimals.service;

import com.motrechko.HappyAnimals.entity.User;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByEmail(String email);
    User findById(Long id);
    void delete(Long id);

}
