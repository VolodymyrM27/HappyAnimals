package com.motrechko.happyanimals.repository;


import com.motrechko.happyanimals.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users where id IN (select employees_id from users_employees where user_id = ?1)", nativeQuery = true)
    List<User> findEmployees(Long id);

    @Query(value = "INSERT INTO users_employees(`user_id`,`employees_id`)VALUES(?1,?2)", nativeQuery = true)
    User saveEmployee(Long id, Long idEmployee);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    boolean existsByEmployeesAllIgnoreCase(User employees);

}
