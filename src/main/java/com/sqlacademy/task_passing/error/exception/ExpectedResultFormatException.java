package com.sqlacademy.task_passing.error.exception;


public class ExpectedResultFormatException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE_FORMAT =
            "Expected result is in a wrong format for task with ID %d. The expected result is:%n%s";


    public ExpectedResultFormatException(long taskId, String expectedResult) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, taskId, expectedResult));
    }
}
