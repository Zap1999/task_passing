package com.sqlacademy.student_progress.dto;

import lombok.Data;

@Data
public class UserQuizResults {
	public UserQuizResults(Long userId, String username) {
        this.userId = userId;
        this.username = username;
	}
	public long userId;
    public String username;
    public QuizResult[] quizResults;
}