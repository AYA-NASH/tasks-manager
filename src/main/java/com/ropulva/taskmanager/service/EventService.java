package com.ropulva.taskmanager.service;

import com.ropulva.taskmanager.controller.dto.EventDTO;
import com.ropulva.taskmanager.mappers.EventMapper;
import com.ropulva.taskmanager.repository.EventRepository;
import com.ropulva.taskmanager.repository.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getAllEvents(){
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events.stream()
                .map(EventMapper.INSTANCE::eventToEventDTO)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        return EventMapper.INSTANCE.eventToEventDTO(event);
    }

    public EventDTO createEvent(EventDTO eventDTO) {
        Event createdEvent = EventMapper.INSTANCE.eventDTOToEvent(eventDTO);
        Event savedEvent = eventRepository.save(createdEvent);
        return EventMapper.INSTANCE.eventToEventDTO(savedEvent);
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent == null) {
            throw new RuntimeException("Event not found");
        }
        if (eventDTO.getTitle() != null) {
            existingEvent.setTitle(eventDTO.getTitle());
        }

        if (eventDTO.getDescription() != null) {
            existingEvent.setDescription(eventDTO.getDescription());
        }

        if (eventDTO.getStartTime() != null) {
            existingEvent.setStartTime(eventDTO.getStartTime());
        }

        if (eventDTO.getEndTime() != null) {
            existingEvent.setEndTime(eventDTO.getEndTime());
        }

//        if (eventDTO.getStatus() != null) {
//            existingEvent.setStatus(eventDTO.getStatus());
//        }

        Event updatedEvent = eventRepository.save(existingEvent);
        return EventMapper.INSTANCE.eventToEventDTO(updatedEvent);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
