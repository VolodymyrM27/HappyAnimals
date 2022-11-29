package com.motrechko.HappyAnimals.repository;


import com.motrechko.HappyAnimals.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
