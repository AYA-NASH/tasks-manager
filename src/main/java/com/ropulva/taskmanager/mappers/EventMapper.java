package com.ropulva.taskmanager.mappers;

import com.ropulva.taskmanager.controller.dto.EventDTO;
import com.ropulva.taskmanager.repository.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    default EventDTO eventToEventDTO(Event event) {
        return new EventDTO(event.getId(),
                event.getOwner(),
                event.getTitle(),
                event.getDescription(),
                event.getStartTime(),
                event.getEndTime()
        );
    }

    default Event eventDTOToEvent(EventDTO eventDTO) {
        return new Event(eventDTO.getId(),
                eventDTO.getOwner(),
                eventDTO.getTitle(),
                eventDTO.getDescription(),
                eventDTO.getStartTime(),
                eventDTO.getEndTime()
        );

    }

}
