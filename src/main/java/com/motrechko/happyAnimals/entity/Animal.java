package com.motrechko.happyAnimals.entity;



import com.motrechko.happyAnimals.enums.AnimalType;
import com.motrechko.happyAnimals.enums.HealthStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Animal extends BaseEntity{
    private String name;
    private double height;
    private double weight;
    private int months;

    @Enumerated(EnumType.STRING)
    private AnimalType type;
    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;
    private String breed;


}

