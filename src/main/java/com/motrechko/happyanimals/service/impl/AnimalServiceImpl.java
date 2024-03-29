package com.motrechko.happyanimals.service.impl;

import com.motrechko.happyanimals.entity.Animal;
import com.motrechko.happyanimals.entity.AnimalHistory;
import com.motrechko.happyanimals.repository.AnimalRepository;
import com.motrechko.happyanimals.service.AnimalHistoryService;
import com.motrechko.happyanimals.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalHistoryService animalHistoryService;

    public AnimalServiceImpl(AnimalRepository animalRepository, AnimalHistoryServiceImpl animalHistoryService) {
        this.animalRepository = animalRepository;
        this.animalHistoryService = animalHistoryService;
    }

    @Override
    public Animal add(Animal animal) {
        Animal newAnimal = animalRepository.save(animal);
        log.info("IN add - animal: {} successfully added", newAnimal);
        return newAnimal;

    }

    @Override
    public List<Animal> getAll() {
        List<Animal> result =   animalRepository.findAll();
        log.info("IN getAll - {} animal found", result.size());
        return result;
    }

    @Override
    public Animal findByName(String name) {
        Animal animal =  animalRepository.findByName(name);
        log.info("IN findByName - animal: {} found by name: {}", animal,name);
        return animal;

    }

    @Override
    public Optional<Animal> findById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        if(animal.isPresent()){
            log.info("IN findById - animal: {} found by id: {}", animal,id);
        } else {
            log.info("IN findById - animal: {} NOT found by id: {}", animal,id);
        }
        return animal;
    }

    @Override
    public void delete(Long id) {
            animalRepository.deleteById(id);
            log.info("IN delete - animal with ID: {} was deleted", id);
    }

    @Override
    public Animal replaceOrAdd(Animal newAnimal, Long id) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animalHistoryService.add(animal);
                    animal.setName(newAnimal.getName());
                    animal.setHeight(newAnimal.getHeight());
                    animal.setWeight(newAnimal.getWeight());
                    animal.setMonths(newAnimal.getMonths());
                    animal.setType(newAnimal.getType());
                    animal.setHealthStatus(newAnimal.getHealthStatus());
                    animal.setBreed(newAnimal.getBreed());
                    return animalRepository.save(animal);
                })
                .orElseGet(() -> {
                    newAnimal.setId(id);
                    return animalRepository.save(newAnimal);
                });
    }

    @Override
    public List<AnimalHistory> getAnimalHistory(Long animalId) {
        return animalHistoryService.getAllHistoryById(animalId);
    }
}
