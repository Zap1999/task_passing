package com.sqlacademy.task_passing.error.exception;


public class UserUnallowedToPostAnswerException extends TaskPassingException {
    private static final String EXCEPTION_MESSAGE = "You are not allowed to post an answer to the task.";
    private static final String ERROR_TEXT = "An answer posted to an unallowed task";


    public UserUnallowedToPostAnswerException() {
        super(EXCEPTION_MESSAGE);
    }

    @Override
    public String getError() {
        return ERROR_TEXT;
    }
}
