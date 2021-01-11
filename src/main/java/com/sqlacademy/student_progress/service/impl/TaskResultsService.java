package com.sqlacademy.student_progress.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sqlacademy.student_progress.dto.QuizResult;
import com.sqlacademy.student_progress.dto.TaskSummary;
import com.sqlacademy.student_progress.dto.UserQuiz;
import com.sqlacademy.student_progress.dto.UserQuizResults;
import com.sqlacademy.student_progress.dto.UserQuizSummary;
import com.sqlacademy.student_progress.dto.UsersCompared;
import com.sqlacademy.student_progress.service.ITaskResultsService;
import com.sqlacademy.task_passing.entity.PassingSummary;
import com.sqlacademy.task_passing.entity.Quiz;
import com.sqlacademy.task_passing.entity.Task;
import com.sqlacademy.task_passing.repository.PassingSummaryRepository;
import com.sqlacademy.task_passing.repository.QuizRepository;
import com.sqlacademy.task_passing.repository.TaskRepository;
import com.sqlacademy.task_passing.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskResultsService implements ITaskResultsService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PassingSummaryRepository passingSummaryRepository;
    private final QuizRepository quizRepository;

    @Override
    public UserQuizResults getUserQuizesResults(long userId) {
        var user = this.userRepository.findById(userId).get();
        var tasks = this.taskRepository.findAll();
        tasks.removeIf(t -> t.getUser().getId() != user.getId());
        var hashMap = new HashMap<Quiz, List<Task>>();
        setHashMap(hashMap, tasks);

        String username = user.getName() + " " + user.getSurname();
        var result = new UserQuizResults(user.getId(), username);

        if (hashMap.size() > 0) {
            var quizResults = new ArrayList<QuizResult>();
            for (var quiz : hashMap.keySet()) {
                var quizResult = new QuizResult();
                quizResult.quizId = quiz.getId();
                quizResult.name = quiz.getName();
                quizResult.actualMark = 0;
                quizResult.maxMark = 0;
                for (var task : hashMap.get(quiz)) {
                    quizResult.maxMark += task.getMaxMark();
                    var passingSummary = this.passingSummaryRepository
                            .findPassingSummaryByTaskIdAndUserId(task.getId(), user.getId()).get();
                    quizResult.actualMark += passingSummary.getIsCorrect() ? task.getMaxMark() : 0;
                }
            }
            result.quizResults = (QuizResult[]) quizResults.toArray();
        }
        return result;
    }

    private void setHashMap(HashMap<Quiz, List<Task>> hashMap, List<Task> tasks) {
        for (var task : tasks) {
            if (!hashMap.containsKey(task.getQuiz())) {
                List<Task> taskList = new ArrayList<Task>();
                taskList.add(task);

                hashMap.put(task.getQuiz(), taskList);
            } else {
                hashMap.get(task.getQuiz()).add(task);
            }
        }
    }

    @Override
    public UserQuizSummary getQuizSummary(UserQuiz userQuiz) {
        var result = new UserQuizSummary();
        var quiz = this.quizRepository.findById(userQuiz.quizId).get();
        var user = this.userRepository.findById(userQuiz.userId).get();
        result.userId = userQuiz.userId;
        result.quizId = userQuiz.quizId;
        result.quizName = quiz.getName();
        var tasks = this.taskRepository.findAll();
        tasks.removeIf(t -> t.getUser().getId() != user.getId() && t.getQuiz().getId() != quiz.getId());
        var taskSummaries = new ArrayList<TaskSummary>();
        if (tasks.size() > 0) {
            for (var task : tasks) {
                result.maxMark += task.getMaxMark();
                var passingSummary = this.passingSummaryRepository
                        .findPassingSummaryByTaskIdAndUserId(task.getId(), user.getId()).get();
                result.actualMark += passingSummary.getIsCorrect() ? task.getMaxMark() : 0;
                taskSummaries.add(setTaskSummary(passingSummary, task));
            }
        }

        result.taskSummaries = (TaskSummary[]) taskSummaries.toArray();

        return result;
    }

    private TaskSummary setTaskSummary(PassingSummary passingSummary, Task task) {
        var taskSummary = new TaskSummary();
        taskSummary.mark = passingSummary.getIsCorrect() ? task.getMaxMark() : 0;
        taskSummary.actualAnswer = passingSummary.getActualAnswer();
        taskSummary.isCorrect = passingSummary.getIsCorrect();
        taskSummary.expectedAnswer = task.getExpectedResult();
        taskSummary.passingSummaryId = passingSummary.getId();
        taskSummary.taskId = task.getId();
        return taskSummary;
    }

    @Override
    public UserQuizSummary[] compareAchivements(UsersCompared users) {
        var summaries = new ArrayList<UserQuizSummary>();
        summaries.add(getQuizSummary(new UserQuiz(users.currentUserId, users.quizId)));
        summaries.add(getQuizSummary(new UserQuiz(users.anotherUserId, users.quizId)));
        return (UserQuizSummary[]) summaries.toArray();
    }
}
