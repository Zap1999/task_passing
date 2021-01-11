package com.sqlacademy.student_progress.service;

import com.sqlacademy.student_progress.dto.UserQuiz;
import com.sqlacademy.student_progress.dto.UserQuizResults;
import com.sqlacademy.student_progress.dto.UserQuizSummary;


public interface ITaskResultsService {
    UserQuizResults getUserQuizesResults(long userId);
    UserQuizSummary getQuizSummary(UserQuiz userTask);
}
