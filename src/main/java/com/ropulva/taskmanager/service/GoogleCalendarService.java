package com.ropulva.taskmanager.service;

import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.*;
import com.ropulva.taskmanager.repository.EventRepository;
import com.ropulva.taskmanager.repository.entity.GoogleCalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class GoogleCalendarService {

    @Autowired
    private com.google.api.services.calendar.Calendar calendarService; // Assuming you have the Calendar instance injected

    private final String calendarSummary = "My Tasks"; // Name of the calendar for your app

    @Autowired
    private EventRepository eventRepository; // Assuming you have the EventRepository defined


    public Event createEvent(Event event) throws IOException {
        String calendarId = createOrGetCalendarId();
        Event createdEvent = calendarService.events().insert(calendarId, event).execute();
        saveEventToDatabase(createdEvent);
        return createdEvent;
    }

    public Event getEvent(String eventId) throws IOException {
        String calendarId = createOrGetCalendarId();
        return calendarService.events().get(calendarId, eventId).execute();
    }

    public Event updateEvent(String eventId, Event event) throws IOException {
        String calendarId = createOrGetCalendarId();
        Event updatedEvent = calendarService.events().update(calendarId, eventId, event).execute();
        saveEventToDatabase(updatedEvent);
        return updatedEvent;
    }

    public void deleteEvent(String eventId) throws IOException {
        String calendarId = createOrGetCalendarId();
        calendarService.events().delete(calendarId, eventId).execute();
        eventRepository.deleteById(Long.valueOf(eventId));
    }

    public List<Event> getAllEvents() throws IOException {
        String calendarId = createOrGetCalendarId();
        Events events = calendarService.events().list(calendarId).execute();
        return events.getItems();
    }

    private String createOrGetCalendarId() throws IOException {
        String calendarId = getCalendarId();
        if (calendarId == null) {
            calendarId = createCalendar();
        }
        return calendarId;
    }

    private String getCalendarId() throws IOException {
        String pageToken = null;
        do {
            CalendarList calendarList = calendarService.calendarList().list().setPageToken(pageToken).execute();
            List<CalendarListEntry> items = calendarList.getItems();
            for (CalendarListEntry calendarListEntry : items) {
                if (calendarSummary.equals(calendarListEntry.getSummary())) {
                    return calendarListEntry.getId();
                }
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);
        return null; // Calendar not found
    }

    private String createCalendar() throws IOException {
        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(calendarSummary);
        calendar.setTimeZone(TimeZone.getDefault().getID());

        com.google.api.services.calendar.model.Calendar createdCalendar = calendarService.calendars().insert(calendar).execute();

        return createdCalendar.getId();

    }

    private void saveEventToDatabase(Event event) {
        GoogleCalendarEvent googleCalendarEvent = new GoogleCalendarEvent();
        googleCalendarEvent.setId(Long.valueOf(event.getId()));
        googleCalendarEvent.setTitle(event.getSummary());
        googleCalendarEvent.setDescription(event.getDescription());

        // Convert start and end times
        if (event.getStart() != null && event.getStart().getDateTime() != null) {
            googleCalendarEvent.setStartTime(
                    dateTimeToLocalDateTime(event.getStart().getDateTime()));
        }

        if (event.getEnd() != null && event.getEnd().getDateTime() != null) {
            googleCalendarEvent.setEndTime(
                    dateTimeToLocalDateTime(event.getEnd().getDateTime()));
        }

        eventRepository.save(googleCalendarEvent);
    }

    private LocalDateTime dateTimeToLocalDateTime(DateTime dateTime) {
        Instant instant = Instant.ofEpochMilli(dateTime.getValue());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
