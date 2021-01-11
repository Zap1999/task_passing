package com.sqlacademy.student_progress.controller;

import com.sqlacademy.student_progress.dto.UserQuiz;
import com.sqlacademy.student_progress.dto.UserQuizResults;
import com.sqlacademy.student_progress.dto.UserQuizSummary;
import com.sqlacademy.student_progress.service.ITaskResultsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/results")
public class ResultsController {
    private final ITaskResultsService taskResultsService;

    @GetMapping()
    public ResponseEntity<UserQuizResults> getUserQuizesResults(@RequestParam long userId) {
        return ResponseEntity.ok(taskResultsService.getUserQuizesResults(userId));
    }

    @PostMapping("/quiz-summary")
    public ResponseEntity<UserQuizSummary> getQuizSummary(@RequestBody UserQuiz userQuiz) {
        return ResponseEntity.ok(taskResultsService.getQuizSummary(userQuiz));
    }
}
