package com.sqlacademy.task_passing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public abstract class TaskPassingSummary {
    private final long taskId;
    private final long userId;
    private final String answerQuery;
}
