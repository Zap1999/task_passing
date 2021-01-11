package com.sqlacademy.student_progress.dto;

import lombok.Data;

@Data
public class TaskSummary {
    public long taskId;
    public long passingSummaryId;
    public String actualAnswer;
    public String expectedAnswer;
    public int mark;
    public Boolean isCorrect;
}
