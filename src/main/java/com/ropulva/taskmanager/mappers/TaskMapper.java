package com.ropulva.taskmanager.mappers;

import com.ropulva.taskmanager.controller.dto.TaskDTO;
import com.ropulva.taskmanager.repository.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


    default TaskDTO taskToTaskDTO(Task task) {
        return new TaskDTO(task.getId(), task.getName(), task.getDescription());
    }


    default Task taskDTOToTask(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(), taskDTO.getName(), taskDTO.getDescription());
    }

}
