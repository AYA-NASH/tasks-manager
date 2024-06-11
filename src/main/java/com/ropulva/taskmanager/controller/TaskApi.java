package com.ropulva.taskmanager.controller;

import com.ropulva.taskmanager.controller.dto.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tasks")
public interface TaskApi {
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks();

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(String id);

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(TaskDTO taskDTO);

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(String id, TaskDTO taskDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(String id);

}
