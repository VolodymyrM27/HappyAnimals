package com.motrechko.happyanimals.service;

import com.motrechko.happyanimals.entity.Task;
import com.motrechko.happyanimals.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user, boolean isEmployee);

    List<User> getAll();

    User findByEmail(String email);

    Optional<User> findById(Long id);

    void delete(Long id);

    User replaceOrAdd(User user, Long id);

    boolean existByEmail(String email);

    Task addTask(Task task, Long employeeId);

    List<Task> getAllTasks(Long employeeId);

}
