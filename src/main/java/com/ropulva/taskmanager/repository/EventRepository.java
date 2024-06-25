package com.ropulva.taskmanager.repository;

import com.ropulva.taskmanager.repository.entity.GoogleCalendarEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<GoogleCalendarEvent, String> {
}
