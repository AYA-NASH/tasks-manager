package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.Entity.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<Long, TaskEntity> tasks = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<TaskEntity> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public TaskEntity findById(Long id) {
        return tasks.get(id);
    }

    @Override
    public TaskEntity save(TaskEntity task) {
        if (task.getId() == null) {
            task.setId(nextId++);
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void deleteById(Long id) {
        tasks.remove(id);
    }
}