package com.ropulva.taskmanager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {
    private String id;
    private String name;
    private String description;
    private LocalDate date;
}