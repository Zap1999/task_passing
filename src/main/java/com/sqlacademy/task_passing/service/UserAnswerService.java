package com.sqlacademy.task_passing.service;


public interface UserAnswerService {
    void recordUserResult(long taskId, long userId, String answer, boolean passed);

    boolean isUserAllowedToPostAnswer(long userId, long taskId);
}
