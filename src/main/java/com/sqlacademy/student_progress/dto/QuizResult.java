package com.sqlacademy.student_progress.dto;

import lombok.Data;

@Data
public class QuizResult {
    public long quizId;
    public String name;
    public Integer maxMark;
    public Integer actualMark;
}