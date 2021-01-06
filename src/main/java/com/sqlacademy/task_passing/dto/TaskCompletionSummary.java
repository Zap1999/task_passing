package com.sqlacademy.task_passing.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = true)
public class TaskCompletionSummary extends TaskPassingSummary {
    private final int mark;

    @Builder
    public TaskCompletionSummary(long taskId, long userId, String answerQuery, int mark) {
        super(taskId, userId, answerQuery);
        this.mark = mark;
    }
}
