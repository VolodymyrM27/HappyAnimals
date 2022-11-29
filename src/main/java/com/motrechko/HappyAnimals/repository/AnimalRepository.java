package com.motrechko.HappyAnimals.repository;

import com.motrechko.HappyAnimals.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository  extends JpaRepository<Animal,Long> {

}
