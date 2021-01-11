package com.sqlacademy.student_progress.service;

import com.sqlacademy.student_progress.dto.UserQuiz;
import com.sqlacademy.student_progress.dto.UserQuizResults;
import com.sqlacademy.student_progress.dto.UserQuizSummary;
import com.sqlacademy.student_progress.dto.UsersCompared;


public interface ITaskResultsService {
    UserQuizResults getUserQuizesResults(long userId);
    UserQuizSummary getQuizSummary(UserQuiz userTask);
    UserQuizSummary[] compareAchivements(UsersCompared users);
}
