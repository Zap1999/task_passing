package com.sqlacademy.task_passing.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User user;
    private LocalDate creationDate;
    private Integer maxMark;
    private String expectedResult;
}
