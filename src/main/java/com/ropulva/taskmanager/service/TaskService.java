package com.ropulva.taskmanager.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.ropulva.taskmanager.controller.dto.TaskDTO;
import com.ropulva.taskmanager.mappers.TaskMapper;
import com.ropulva.taskmanager.repository.EventRepository;
import com.ropulva.taskmanager.repository.TaskRepository;
import com.ropulva.taskmanager.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;
    private final GoogleCalendarService googleCalendarService;

    @Autowired
    public TaskService(EventRepository eventRepository, TaskRepository taskRepository, GoogleCalendarService googleCalendarService) {
        this.eventRepository = eventRepository;
        this.taskRepository = taskRepository;
        this.googleCalendarService = googleCalendarService;
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper.INSTANCE::taskToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO) throws IOException {
        Task task = TaskMapper.INSTANCE.taskDTOToTask(taskDTO);


        // CREATE GOOGLE CALENDAR EVENT
        // TODO: Should be done like a transaction
        Event taskEvent = new Event();

        taskEvent.setSummary(task.getName());
        taskEvent.setDescription(task.getDescription());

        DateTime startDateTime = new DateTime(task.getStartDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        DateTime endDateTime = new DateTime(task.getEndDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        taskEvent.setStart(new EventDateTime().setDateTime(new DateTime(String.valueOf(startDateTime))));
        taskEvent.setEnd(new EventDateTime().setDateTime(new DateTime(String.valueOf(endDateTime))));

        Event createdEvent = googleCalendarService.createEvent(taskEvent);
        task.setEventId(createdEvent.getId());

        Task createdTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.taskToTaskDTO(createdTask);
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return TaskMapper.INSTANCE.taskToTaskDTO(task);
    }

    public List<TaskDTO> getTasksByName(String name) {

        List<Task> tasks = taskRepository.findByName(name);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::taskToTaskDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) throws IOException {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        Event existingEvent = googleCalendarService.getEvent(existingTask.getEventId());
        if (taskDTO.getName() != null) {
            existingTask.setName(taskDTO.getName());
            existingEvent.setSummary(taskDTO.getName());
        }
        if (taskDTO.getDescription() != null) {
            existingTask.setDescription(taskDTO.getDescription());
            existingEvent.setDescription(taskDTO.getDescription());
        }
        if (taskDTO.getStartDate() != null) {
            existingTask.setStartDate(taskDTO.getStartDate());
            existingEvent.setStart(new EventDateTime().setDateTime(new DateTime(taskDTO.getStartDate().toString())));
        }
        if (taskDTO.getEndDate() != null) {
            existingTask.setEndDate(taskDTO.getEndDate());
            existingEvent.setEnd(new EventDateTime().setDateTime(new DateTime(taskDTO.getEndDate().toString())));
        }

        Task updatedTask = taskRepository.save(existingTask);
        googleCalendarService.updateEvent(existingTask.getEventId(), existingEvent);
        return TaskMapper.INSTANCE.taskToTaskDTO(updatedTask);
    }

     public TaskDTO updateTasksByName(String name, TaskDTO taskDTO) throws IOException {
          List<Task> tasks = taskRepository.findByName(name);
          Event existingEvent = googleCalendarService.getEvent(tasks.get(0).getEventId());
          if (taskDTO.getName() != null) {
                tasks.forEach(task -> {
                 task.setName(taskDTO.getName());
                });
                existingEvent.setSummary(taskDTO.getName());
          }
          if (taskDTO.getDescription() != null) {
                tasks.forEach(task -> {
                 task.setDescription(taskDTO.getDescription());
                });
                existingEvent.setDescription(taskDTO.getDescription());
          }
          if (taskDTO.getStartDate() != null) {
                tasks.forEach(task -> {
                 task.setStartDate(taskDTO.getStartDate());
                });
                existingEvent.setStart(new EventDateTime().setDateTime(new DateTime(taskDTO.getStartDate().toString())));
          }
          if (taskDTO.getEndDate() != null) {
                tasks.forEach(task -> {
                 task.setEndDate(taskDTO.getEndDate());
                });
                existingEvent.setEnd(new EventDateTime().setDateTime(new DateTime(taskDTO.getEndDate().toString())));
          }

          taskRepository.saveAll(tasks);
          googleCalendarService.updateEvent(tasks.get(0).getEventId(), existingEvent);
          return TaskMapper.INSTANCE.taskToTaskDTO(tasks.get(0));
     }

    public void deleteTask(Long id) throws IOException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.deleteById(id);
        googleCalendarService.deleteEvent(task.getEventId());

        taskRepository.deleteById(id);
    }

    public void deleteTasksByName(String name){
        List<Task> tasks = taskRepository.findByName(name);
        tasks.forEach(task -> {
            try {
                googleCalendarService.deleteEvent(task.getEventId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            taskRepository.delete(task);
        });

    }

    public void deleteAllTasks() {
        Iterable<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> {
            try {
                googleCalendarService.deleteEvent(task.getEventId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            taskRepository.delete(task);
        });
    }


}