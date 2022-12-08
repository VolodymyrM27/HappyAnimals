package com.motrechko.happyanimals.service;

import com.motrechko.happyanimals.entity.Animal;
import com.motrechko.happyanimals.entity.AnimalHistory;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    Animal add(Animal animal);

    List<Animal> getAll();

    Animal findByName(String name);

    Optional<Animal> findById(Long id);

    void delete(Long id);

    Animal replaceOrAdd(Animal animal, Long id);

    List<AnimalHistory> getAnimalHistory(Long animalId);
}
