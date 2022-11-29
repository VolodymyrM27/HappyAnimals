package com.motrechko.HappyAnimals.entity;



import com.motrechko.HappyAnimals.enums.AnimalType;
import com.motrechko.HappyAnimals.enums.HealthStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Animal {
    private @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private double height;
    private double weight;
    private int months;

    @Enumerated(EnumType.STRING)
    private AnimalType type;
    private HealthStatus healthStatus;
    private String breed;

    public Animal() {
    }

    public Animal( String name, double height, double weight, int months, AnimalType type,String breed, HealthStatus healthStatus) {

        this.name = name;
        this.height = height;
        this.weight = weight;
        this.months = months;
        this.type = type;
        this.healthStatus = healthStatus;
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.height, height) == 0 && Double.compare(animal.weight, weight) == 0 && months == animal.months && Objects.equals(id, animal.id) && Objects.equals(name, animal.name) && type == animal.type && healthStatus == animal.healthStatus && Objects.equals(breed, animal.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, height, weight, months, type, healthStatus, breed);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", months=" + months +
                ", type=" + type +
                ", healthStatus=" + healthStatus +
                ", breed='" + breed + '\'' +
                '}';
    }
}

