package com.ropulva.taskmanager.mappers;

import com.ropulva.taskmanager.controller.dto.EventDTO;
import com.ropulva.taskmanager.repository.entity.GoogleCalendarEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    default EventDTO eventToEventDTO(GoogleCalendarEvent GoogleCalendarEvent) {
        return new EventDTO(GoogleCalendarEvent.getId(),
                GoogleCalendarEvent.getOwner(),
                GoogleCalendarEvent.getTitle(),
                GoogleCalendarEvent.getDescription(),
                GoogleCalendarEvent.getStartTime(),
                GoogleCalendarEvent.getEndTime()
        );
    }

    default GoogleCalendarEvent eventDTOToEvent(EventDTO eventDTO) {
        return new GoogleCalendarEvent(eventDTO.getId(),
                eventDTO.getOwner(),
                eventDTO.getTitle(),
                eventDTO.getDescription(),
                eventDTO.getStartTime(),
                eventDTO.getEndTime()
        );

    }

}
