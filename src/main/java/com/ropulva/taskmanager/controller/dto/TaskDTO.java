package com.ropulva.taskmanager.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("description")
    private String description;
}