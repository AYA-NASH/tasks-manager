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


    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper.INSTANCE::taskToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        System.out.println("taskDTO" + taskDTO);
        Task task = TaskMapper.INSTANCE.taskDTOToTask(taskDTO);
        Task createdTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.taskToTaskDTO(createdTask);
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        return TaskMapper.INSTANCE.taskToTaskDTO(task);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = TaskMapper.INSTANCE.taskDTOToTask(taskDTO);
        Task updatedTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.taskToTaskDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}