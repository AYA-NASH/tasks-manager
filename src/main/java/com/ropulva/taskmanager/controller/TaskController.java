package com.ropulva.taskmanager.controller;
import com.ropulva.taskmanager.service.TaskService;
import com.ropulva.taskmanager.controller.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return null;
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return null;
    }
}
