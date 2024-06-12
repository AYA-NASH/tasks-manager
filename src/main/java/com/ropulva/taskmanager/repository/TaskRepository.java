package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>{

}