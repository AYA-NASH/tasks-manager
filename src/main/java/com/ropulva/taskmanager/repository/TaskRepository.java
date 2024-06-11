package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.Entity.TaskEntity;

import java.util.List;


public interface TaskRepository {
    List<TaskEntity> findAll();
    TaskEntity findById(Long id);
    TaskEntity save(TaskEntity task);
    void deleteById(Long id);
}