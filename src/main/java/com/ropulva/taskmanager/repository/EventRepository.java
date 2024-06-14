package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
