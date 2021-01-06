package com.sqlacademy.task_passing.error.exception;


public class TaskNotFoundException extends TaskPassingException {
    private static final String EXCEPTION_MESSAGE_FORMAT = "Could not find any task with id %d.";
    private static final String ERROR_TEXT = "Task not found";


    public TaskNotFoundException(long taskId) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, taskId));
    }

    @Override
    public String getError() {
        return ERROR_TEXT;
    }
}
