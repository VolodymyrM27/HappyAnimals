package com.motrechko.happyanimals.dto;


import com.motrechko.happyanimals.entity.Task;
import com.motrechko.happyanimals.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private String taskField;
    private Date date;


    public static Task fromDto(TaskDto taskDto) {
        Task task = new Task();
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setTaskField(taskDto.taskField);
        task.setDate(taskDto.getDate());
        return task;
    }

    public static TaskDto fromTask(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskField(task.getTaskField());
        taskDto.setDate(task.getDate());
        return taskDto;
    }
}
