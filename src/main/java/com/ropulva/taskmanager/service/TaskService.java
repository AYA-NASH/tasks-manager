package com.ropulva.taskmanager.service;

import com.ropulva.taskmanager.controller.dto.TaskDTO;
import com.ropulva.taskmanager.mappers.TaskMapper;
import com.ropulva.taskmanager.repository.TaskRepository;
import com.ropulva.taskmanager.repository.entity.Task;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::taskToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.taskDTOToTask(taskDTO);
        Task createdTask = taskRepository.save(task);
        return taskMapper.taskToTaskDTO(createdTask);
    }

    public TaskDTO getTaskById(String id) {
        Task task = taskRepository.findById(id);
        return taskMapper.taskToTaskDTO(task);
    }

    public TaskDTO updateTask(String id, TaskDTO taskDTO) {
        Task task = taskMapper.taskDTOToTask(taskDTO);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.taskToTaskDTO(updatedTask);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }


}