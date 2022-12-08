package com.motrechko.happyanimals.service;

import com.motrechko.happyanimals.entity.Animal;
import com.motrechko.happyanimals.entity.AnimalHistory;

import java.util.List;

public interface AnimalHistoryService {
    List<AnimalHistory> getAllHistoryById(Long animalId);

    AnimalHistory add(Animal animal);
}
