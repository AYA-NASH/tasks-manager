package com.example.Task.Management.Controller;
import com.example.Task.Management.Entity.TaskEntity;
import com.example.Task.Management.Service.TaskService;
import com.example.Task.Management.Service.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskServiceInterface taskService;

    @Autowired
    public TaskController(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
//        TaskDTO task = taskService.getTaskById(id);
//        return ResponseEntity.ok(task);
//    }
//
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
//        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
//        return ResponseEntity.ok(updatedTask);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
//        taskService.deleteTask(id);
//        return ResponseEntity.noContent().build();
//    }
}
