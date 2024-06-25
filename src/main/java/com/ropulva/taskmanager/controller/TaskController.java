package com.ropulva.taskmanager.controller;
import com.ropulva.taskmanager.service.TaskService;
import com.ropulva.taskmanager.controller.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<TaskDTO> getTaskById(Long id) {
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getTasksByName(String name) {
        List<TaskDTO> tasks = taskService.getTasksByName(name);
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskDTO taskDTO) throws IOException {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(Long id, TaskDTO taskDTO) {
        TaskDTO updatedTask = null;
        try {
            updatedTask = taskService.updateTask(id, taskDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(updatedTask);
    }

    @Override
    public ResponseEntity<TaskDTO> updateTasksByName(String name, TaskDTO taskDTO) {
        TaskDTO updatedTask = null;
        try {
            updatedTask = taskService.updateTasksByName(name, taskDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(updatedTask);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        try {
            taskService.deleteTask(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteTasksByName(String name) {
        taskService.deleteTasksByName(name);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteAllTasks() {
        taskService.deleteAllTasks();
        return ResponseEntity.noContent().build();
    }
}
