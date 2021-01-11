package com.sqlacademy.student_progress.dto;

import lombok.Data;

@Data
public class UserQuizSummary {
    public long userId;
    public long quizId;
    public String quizName;
    public int maxMark;
    public int actualMark;
    public TaskSummary[] taskSummaries;
}