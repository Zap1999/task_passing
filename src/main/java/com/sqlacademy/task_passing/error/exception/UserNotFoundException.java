package com.sqlacademy.task_passing.error.exception;


public class UserNotFoundException extends TaskPassingException {
    private static final String EXCEPTION_MESSAGE_FORMAT = "Could not find any user with id %d.";
    private static final String ERROR_TEXT = "User not found";


    public UserNotFoundException(long userId) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, userId));
    }

    @Override
    public String getError() {
        return ERROR_TEXT;
    }
}
