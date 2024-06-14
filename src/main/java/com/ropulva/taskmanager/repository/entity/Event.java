package com.ropulva.taskmanager.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "owner")
    private String owner;


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "calendar_id")
//    private Calendar calendar;


}
