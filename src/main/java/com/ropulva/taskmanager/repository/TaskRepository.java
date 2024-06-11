package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.entity.Task;

import java.util.List;


public interface TaskRepository{
    List<Task> findAll();
    Task findById(String id);
    Task save(Task task);
    void deleteById(String id);
}