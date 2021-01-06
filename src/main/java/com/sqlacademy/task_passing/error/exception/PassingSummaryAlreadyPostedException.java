package com.sqlacademy.task_passing.error.exception;

public class PassingSummaryAlreadyPostedException extends TaskPassingException {
    private static final String EXCEPTION_MESSAGE_FORMAT =
            "A task passing summary for task ID %d from user ID %d is already saved in the database.";
    private static final String ERROR_TEXT = "Passing summary already posted";


    public PassingSummaryAlreadyPostedException(long taskId, long userId) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, taskId, userId));
    }

    @Override
    public String getError() {
        return ERROR_TEXT;
    }
}
