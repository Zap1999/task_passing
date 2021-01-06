package com.sqlacademy.task_passing.error.exception;

public abstract class TaskPassingException extends RuntimeException {
    public TaskPassingException(String message) {
        super(message);
    }

    public abstract String getError();
}
