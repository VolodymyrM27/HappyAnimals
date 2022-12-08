package com.motrechko.happyanimals.dto;

import com.motrechko.happyanimals.entity.Animal;
import com.motrechko.happyanimals.enums.AnimalType;
import com.motrechko.happyanimals.enums.HealthStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.motrechko.happyanimals.entity.Animal} entity
 */
@Data
public class AnimalDto implements Serializable {
    private final String name;
    private final double height;
    private final double weight;
    private final int months;
    private final AnimalType type;
    private final HealthStatus healthStatus;
    private final String breed;

    public List<AnimalDto> fromAnimalList(List<Animal> animals){
        List<AnimalDto> result = new ArrayList<>();
        animals.forEach(animal -> result.add(
                new AnimalDto(
                        animal.getName(),
                        animal.getHeight(),
                        animal.getWeight(),
                        animal.getMonths(),
                        animal.getType(),
                        animal.getHealthStatus(),
                        animal.getBreed()
                )
        ));
        return result;
    }

    public static AnimalDto fromAnimal(Animal animal){
        return new AnimalDto(
                animal.getName(),
                animal.getHeight(),
                animal.getWeight(),
                animal.getMonths(),
                animal.getType(),
                animal.getHealthStatus(),
                animal.getBreed()
        );
    }

    public static Animal fromDto(AnimalDto animalDto){
        Animal animal = new Animal();
        animal.setName(animalDto.getName());
        animal.setHeight(animalDto.getHeight());
        animal.setWeight(animalDto.getWeight());
        animal.setMonths(animalDto.getMonths());
        animal.setType(animalDto.getType());
        animal.setHealthStatus(animalDto.getHealthStatus());
        animal.setBreed(animalDto.getBreed());
        return animal;
    }
}