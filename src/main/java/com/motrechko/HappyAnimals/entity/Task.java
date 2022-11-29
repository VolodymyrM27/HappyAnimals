package com.motrechko.HappyAnimals.entity;

import com.motrechko.HappyAnimals.enums.TaskStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class Task extends BaseEntity   {
    private String task;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
