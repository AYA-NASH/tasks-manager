package com.ropulva.taskmanager.controller;

import com.ropulva.taskmanager.controller.dto.TaskDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tasks")
public interface TaskApi {
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks();

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(Long id);

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO);

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(Long id, TaskDTO taskDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(Long id);

}
