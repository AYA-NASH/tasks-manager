package com.ropulva.taskmanager.controller;

import com.ropulva.taskmanager.controller.dto.TaskDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/tasks")
public interface TaskApi {
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks();

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id);

    @GetMapping(params = "name")
    public ResponseEntity<List<TaskDTO>> getTasksByName(@RequestParam String name);

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) throws IOException;

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO);

    @PutMapping(params = "name")
    public ResponseEntity<TaskDTO> updateTasksByName(@RequestParam String name, @RequestBody TaskDTO taskDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id);

    @DeleteMapping(params = "name")
    public ResponseEntity<Void> deleteTasksByName(@RequestParam String name);

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTasks();
}
