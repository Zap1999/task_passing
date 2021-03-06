package com.sqlacademy.task_passing.controller;

import com.sqlacademy.task_passing.dto.AnswerPostDto;
import com.sqlacademy.task_passing.dto.TaskPassingSummary;
import com.sqlacademy.task_passing.service.TaskPassingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/passing")
public class TaskPassingController {
    private final TaskPassingService passingService;


    @PostMapping
    public ResponseEntity<TaskPassingSummary> postAnswer(@RequestBody AnswerPostDto answer) {
        return ResponseEntity.ok(passingService.postAnswer(answer));
    }
}
