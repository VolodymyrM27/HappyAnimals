package com.motrechko.HappyAnimals.repository;

import com.motrechko.HappyAnimals.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
