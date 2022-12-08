package com.motrechko.happyanimals.repository;

import com.motrechko.happyanimals.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository  extends JpaRepository<Animal,Long> {

    Animal findByName(String name);
}
