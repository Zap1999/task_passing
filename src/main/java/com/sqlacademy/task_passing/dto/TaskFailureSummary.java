package com.sqlacademy.task_passing.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = true)
public class TaskFailureSummary extends TaskPassingSummary {
    private final String failureReason;
    private final String queryFixSuggestion;

    @Builder
    public TaskFailureSummary(long taskId, long userId, String answerQuery, String failureReason, String queryFixSuggestion) {
        super(taskId, userId, answerQuery);
        this.failureReason = failureReason;
        this.queryFixSuggestion = queryFixSuggestion;
    }
}
