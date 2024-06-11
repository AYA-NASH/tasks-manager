package com.example.Task.Management.Repository;

import com.example.Task.Management.Entity.TaskEntity;

import java.util.List;


public interface TaskRepositoryInterface {
    List<TaskEntity> findAll();
    TaskEntity findById(Long id);
    TaskEntity save(TaskEntity task);
    void deleteById(Long id);
}