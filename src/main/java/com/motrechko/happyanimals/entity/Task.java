package com.motrechko.happyanimals.entity;

import com.motrechko.happyanimals.enums.TaskStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Task extends BaseEntity {
    private String taskField;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    private User user;
}
