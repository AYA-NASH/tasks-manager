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

        googleCalendarService.createEvent(taskEvent);

        Task createdTask = taskRepository.save(task);
        return TaskMapper.INSTANCE.taskToTaskDTO(createdTask);
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return TaskMapper.INSTANCE.taskToTaskDTO(task);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (taskDTO.getName() != null) {
            existingTask.setName(taskDTO.getName());
        }
        if (taskDTO.getDescription() != null) {
            existingTask.setDescription(taskDTO.getDescription());
        }

        Task updatedTask = taskRepository.save(existingTask);
        return TaskMapper.INSTANCE.taskToTaskDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}