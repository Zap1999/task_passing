package com.sqlacademy.task_passing.error.handler;

import com.sqlacademy.task_passing.error.exception.TaskPassingException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;


@Log
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String UNEXPECTED_EXCEPTION_MESSAGE = "The application has encountered an unknown error.";
    private static final String UNEXPECTED_EXCEPTION_ERROR = "An unexpected error occurred in Task Passing applicaiton.";


    @ExceptionHandler(TaskPassingException.class)
    public ResponseEntity<ApiError> handleTaskPassingException(HttpServletRequest request, TaskPassingException e) {
        log.log(Level.INFO, "Task Passing Exception", e);

        final var responseStatus = HttpStatus.BAD_REQUEST;
        final var apiError = new ApiError(responseStatus, e.getError(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(responseStatus).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpectedException(HttpServletRequest request, Exception e) {
        log.log(Level.SEVERE, UNEXPECTED_EXCEPTION_MESSAGE, e);

        final var responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final var apiError = new ApiError(responseStatus, UNEXPECTED_EXCEPTION_ERROR,
                UNEXPECTED_EXCEPTION_MESSAGE, request.getRequestURI());
        return ResponseEntity.status(responseStatus).body(apiError);
    }
}
