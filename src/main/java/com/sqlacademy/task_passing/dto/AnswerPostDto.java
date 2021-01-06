package com.sqlacademy.task_passing.dto;

import lombok.Data;


@Data
public class AnswerPostDto {
    private String answer;
    private long userId;
    private long taskId;
}
