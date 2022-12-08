package com.motrechko.happyanimals.service.impl;

import com.motrechko.happyanimals.entity.Animal;
import com.motrechko.happyanimals.entity.AnimalHistory;
import com.motrechko.happyanimals.repository.AnimalHistoryRepository;
import com.motrechko.happyanimals.repository.AnimalRepository;
import com.motrechko.happyanimals.service.AnimalHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AnimalHistoryServiceImpl implements AnimalHistoryService {
    private final AnimalRepository animalRepository;
    private final AnimalHistoryRepository repository;

    public AnimalHistoryServiceImpl(AnimalHistoryRepository repository,
                                    AnimalRepository animalRepository) {
        this.repository = repository;
        this.animalRepository = animalRepository;
    }

    @Override
    public List<AnimalHistory> getAllHistoryById(Long animalId) {
        List<AnimalHistory> animalHistories = repository.findByAnimal_Id(animalId);
        log.info("IN getAllHistoryById - {} animalHistories found", animalHistories.size());
        return animalHistories;
    }

    @Override
    public AnimalHistory add(Animal animal) {
        AnimalHistory animalHistory = new AnimalHistory();
        animalHistory.setAnimal(animal);
        animalHistory.setName(animal.getName());
        animalHistory.setWeight(animal.getWeight());
        animalHistory.setHeight(animal.getHeight());
        animalHistory.setMonths(animal.getMonths());
        return repository.save(animalHistory);
    }
}
