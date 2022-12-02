package com.motrechko.happyAnimals.repository;

import com.motrechko.happyAnimals.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
