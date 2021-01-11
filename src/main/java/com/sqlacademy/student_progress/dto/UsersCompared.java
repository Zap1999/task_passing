package com.sqlacademy.student_progress.dto;

import lombok.Data;

@Data
public class UsersCompared {
    public long currentUserId;
    public long anotherUserId;
    public long quizId;
}