package com.sqlacademy.student_progress.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserQuiz {
    public long userId;
    public long quizId;
}