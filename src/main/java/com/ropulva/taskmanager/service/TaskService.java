package com.ropulva.taskmanager.service;
import com.ropulva.taskmanager.controller.dto.TaskDTO;
import com.ropulva.taskmanager.repository.TaskRepository;
import com.ropulva.taskmanager.repository.Entity.TaskEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService{

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public TaskDTO getTaskById(Long id) {
//        TaskEntity task = taskRepository.findById(id);
//        if (task == null) {
//            throw new RuntimeException("Task not found");
//        }
//        return convertToTaskDTO(task);
//    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = convertToEntity(taskDTO);
        TaskEntity savedTask = taskRepository.save(taskEntity);
        return convertToTaskDTO(savedTask);
    }

//    @Override
//    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
//        TaskEntity taskEntity = taskRepository.findById(id);
//        if (taskEntity == null) {
//            throw new RuntimeException("Task not found");
//        }
//        taskEntity.setName(taskDTO.getName());
//        taskEntity.setDescription(taskDTO.getDescription());
//        taskEntity.setDate(taskDTO.getDate());
//        TaskEntity updatedTask = taskRepository.save(taskEntity);
//        return convertToTaskDTO(updatedTask);
//    }

//    @Override
//    public void deleteTask(Long id) {
//        taskRepository.deleteById(id);
//    }

    private TaskDTO convertToTaskDTO(TaskEntity taskEntity) {
        return new TaskDTO(
                taskEntity.getId(),
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getDate()
        );
    }

    private TaskEntity convertToEntity(TaskDTO taskDTO) {
        return new TaskEntity(
                taskDTO.getId(),
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getDate()
        );
    }
}