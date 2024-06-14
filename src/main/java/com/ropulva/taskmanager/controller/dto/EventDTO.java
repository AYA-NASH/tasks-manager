package com.ropulva.taskmanager.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;


    @JsonProperty("owner")
    private String owner;


    @JsonProperty("title")
    private String title;


    @JsonProperty("description")
    private String description;



    @JsonProperty("startTime")
    private LocalDateTime startTime;


    @JsonProperty("endTime")
    private LocalDateTime endTime;
}
