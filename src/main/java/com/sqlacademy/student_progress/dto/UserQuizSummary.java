package com.sqlacademy.student_progress.dto;

import lombok.Data;

@Data
public class UserQuizSummary {
    public long userId;
    public long quizId;
    public int maxMark;
    public int actualMark;
    public TaskSummary[] taskSummaries;
}