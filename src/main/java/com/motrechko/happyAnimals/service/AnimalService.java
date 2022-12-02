package com.motrechko.happyAnimals.service;

import com.motrechko.happyAnimals.entity.Animal;
import com.motrechko.happyAnimals.entity.User;


import java.util.List;
import java.util.Optional;

public interface AnimalService {
    Animal add(Animal animal);
    List<Animal> getAll();
    Animal findByName(String name);
    Optional<Animal> findById(Long id);
    void delete(Long id);
    Animal replaceOrAdd(Animal animal, Long id);
}
