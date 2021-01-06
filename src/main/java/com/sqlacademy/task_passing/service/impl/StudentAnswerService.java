package com.sqlacademy.task_passing.service.impl;

import com.sqlacademy.task_passing.entity.PassingSummary;
import com.sqlacademy.task_passing.entity.Task;
import com.sqlacademy.task_passing.entity.User;
import com.sqlacademy.task_passing.error.exception.PassingSummaryAlreadyPostedException;
import com.sqlacademy.task_passing.error.exception.UserNotFoundException;
import com.sqlacademy.task_passing.repository.PassingSummaryRepository;
import com.sqlacademy.task_passing.repository.TaskRepository;
import com.sqlacademy.task_passing.repository.UserRepository;
import com.sqlacademy.task_passing.service.UserAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class StudentAnswerService implements UserAnswerService {
    private static final String ALLOWED_USER_ROLE = "role1";
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PassingSummaryRepository passingSummaryRepository;


    @Override
    public void recordUserResult(long taskId, long userId, String answer, boolean passed) {
        if (passingSummaryRepository.findPassingSummaryByTaskIdAndUserId(taskId, userId).isPresent()) {
            throw new PassingSummaryAlreadyPostedException(taskId, userId);
        }

        final var passingSummary = new PassingSummary();
        passingSummary.setActualAnswer(answer);
        final var user = new User();
        user.setId(userId);
        passingSummary.setUser(user);
        final var task = new Task();
        task.setId(taskId);
        passingSummary.setTask(task);
        passingSummary.setIsCorrect(passed);
        passingSummaryRepository.save(passingSummary);
    }

    @Override
    public boolean isUserAllowedToPostAnswer(long userId, long taskId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .getRole().getName().equals(ALLOWED_USER_ROLE);
    }
}
