package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final Map<String, Task> tasks = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }

    @Override
    public Task save(Task task) {
//        if (task.getId() == null) {
//            Integer.valueOf(task.getId());
//            task.setId(nextId++);
//        }
//        tasks.put(task.getId(), task);
//        return task;
        return null;
    }

    @Override
    public void deleteById(String id) {
        tasks.remove(id);
    }
}