package com.motrechko.happyAnimals.repository;


import com.motrechko.happyAnimals.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    Boolean existsByEmail(String email);


}
