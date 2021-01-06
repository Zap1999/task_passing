package com.sqlacademy.task_passing.error.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class ApiError {
    private String timestamp;
    private String status;
    private String error;
    private String message;
    private String path;


    public ApiError(HttpStatus status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.status = String.valueOf(status.value());
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
