package com.sqlacademy.task_passing.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "passing_summaries")
public class PassingSummary {
    @Id
    @GeneratedValue
    private Long id;
    private String actualAnswer;
    private Boolean isCorrect;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
