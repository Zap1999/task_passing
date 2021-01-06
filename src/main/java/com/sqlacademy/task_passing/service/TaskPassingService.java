package com.sqlacademy.task_passing.service;

import com.sqlacademy.task_passing.dto.AnswerPostDto;
import com.sqlacademy.task_passing.dto.TaskPassingSummary;


public interface TaskPassingService {
    TaskPassingSummary postAnswer(AnswerPostDto answer);
}
