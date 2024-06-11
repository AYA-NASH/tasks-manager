package com.example.Task.Management.Service;

import com.example.Task.Management.Controller.TaskDTO;

import java.util.List;

public interface TaskServiceInterface {
    List<TaskDTO> getAllTasks();
//    TaskDTO getTaskById(Long id);
    TaskDTO createTask(TaskDTO taskDTO);
//    TaskDTO updateTask(Long id, TaskDTO taskDTO);
//    void deleteTask(Long id);
}