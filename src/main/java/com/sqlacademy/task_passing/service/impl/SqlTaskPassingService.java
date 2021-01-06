package com.sqlacademy.task_passing.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqlacademy.task_passing.dto.AnswerPostDto;
import com.sqlacademy.task_passing.dto.TaskCompletionSummary;
import com.sqlacademy.task_passing.dto.TaskFailureSummary;
import com.sqlacademy.task_passing.dto.TaskPassingSummary;
import com.sqlacademy.task_passing.error.exception.ExpectedResultFormatException;
import com.sqlacademy.task_passing.error.exception.TaskNotFoundException;
import com.sqlacademy.task_passing.error.exception.UserUnallowedToPostAnswerException;
import com.sqlacademy.task_passing.repository.TaskRepository;
import com.sqlacademy.task_passing.service.*;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class SqlTaskPassingService implements TaskPassingService {
    private static final String INVALID_SYNTAX_FAILURE_REASON = "Your query's syntax is invalid.";
    private static final String INVALID_SYNTAX_QUERY_SUGGESTION_FORMAT =
            "Your query's syntax is invalid. Please learn more about %s statements' syntax.";
    private static final String INVALID_SYNTAX_QUERY_SUGGESTION_TYPE_NOT_DEFINED =
            "Your query's syntax is invalid. Please learn more about the syntax of query you were trying to build.";
    private static final String INVALID_RESULT_FAILURE_REASON =
            "Your query's syntax is valid, but results differ from expected ones.";
    private static final String INVALID_RESULT_QUERY_SUGGESTION = "Please check conditions/data in your statement.";

    private final UserAnswerService userAnswerService;
    private final QueryExecutionService queryExecutionService;
    private final SyntaxAnalysisService syntaxAnalysisService;
    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;


    private static boolean areListsOfMapsEqual(List<? extends Map<String, Object>> collection1, List<? extends Map<String, Object>> collection2) {
        if (collection1.size() != collection2.size()) {
            return false;
        }

        for (int i = 0; i < collection1.size(); i++) {
            final var map = collection1.get(i);
            final var allMatch = collection2.get(i).entrySet().stream()
                    .allMatch(e -> e.getValue().equals(map.get(e.getKey())));
            if (!allMatch) {
                return false;
            }
        }

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TaskPassingSummary postAnswer(AnswerPostDto answer) {
        if (!userAnswerService.isUserAllowedToPostAnswer(answer.getUserId(), answer.getTaskId())) {
            throw new UserUnallowedToPostAnswerException();
        }
        if (!syntaxAnalysisService.isValidSyntax(answer.getAnswer())) {
            userAnswerService.recordUserResult(answer.getTaskId(), answer.getUserId(), answer.getAnswer(), false);
            return generateInvalidSyntaxSummary(answer, syntaxAnalysisService.defineSqlQueryType(answer.getAnswer()));
        }

        final var expectedResultString = taskRepository.findById(answer.getTaskId())
                .orElseThrow(() -> new TaskNotFoundException(answer.getTaskId())).getExpectedResult();
        try {
            final var result = queryExecutionService.executeQuery(answer.getAnswer());
            final var expectedResult = objectMapper.readValue(expectedResultString, List.class);

            if (areListsOfMapsEqual(result, expectedResult)) {
                userAnswerService.recordUserResult(answer.getTaskId(), answer.getUserId(), answer.getAnswer(), true);
                return generateCompletionSummary(answer);
            } else {
                userAnswerService.recordUserResult(answer.getTaskId(), answer.getUserId(), answer.getAnswer(), false);
                return generateFailureSummary(answer);
            }
        } catch (JsonProcessingException e) {
            throw new ExpectedResultFormatException(answer.getTaskId(), expectedResultString);
        }
    }

    private TaskFailureSummary generateFailureSummary(AnswerPostDto answer) {
        return TaskFailureSummary.builder()
                .taskId(answer.getTaskId())
                .userId(answer.getUserId())
                .answerQuery(answer.getAnswer())
                .failureReason(INVALID_RESULT_FAILURE_REASON)
                .queryFixSuggestion(INVALID_RESULT_QUERY_SUGGESTION)
                .build();
    }

    private TaskFailureSummary generateInvalidSyntaxSummary(AnswerPostDto answer, @Nullable SqlQueryType queryType) {
        return TaskFailureSummary.builder()
                .taskId(answer.getTaskId())
                .userId(answer.getUserId())
                .answerQuery(answer.getAnswer())
                .failureReason(INVALID_SYNTAX_FAILURE_REASON)
                .queryFixSuggestion(
                        queryType == null ?
                                INVALID_SYNTAX_QUERY_SUGGESTION_TYPE_NOT_DEFINED :
                                String.format(INVALID_SYNTAX_QUERY_SUGGESTION_FORMAT, queryType.toString())
                ).build();
    }

    private TaskCompletionSummary generateCompletionSummary(AnswerPostDto answer) {
        return TaskCompletionSummary.builder()
                .taskId(answer.getTaskId())
                .userId(answer.getUserId())
                .answerQuery(answer.getAnswer())
                .mark(taskRepository.findById(answer.getTaskId())
                        .orElseThrow(() -> new TaskNotFoundException(answer.getTaskId()))
                        .getMaxMark()
                ).build();
    }
}
