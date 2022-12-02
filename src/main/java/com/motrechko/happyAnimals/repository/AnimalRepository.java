package com.motrechko.happyAnimals.repository;

import com.motrechko.happyAnimals.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository  extends JpaRepository<Animal,Long> {

    Animal findByName(String name);
}
