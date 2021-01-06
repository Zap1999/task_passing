package com.sqlacademy.task_passing.repository;

import com.sqlacademy.task_passing.entity.PassingSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PassingSummaryRepository extends JpaRepository<PassingSummary, Long> {
    Optional<PassingSummary> findPassingSummaryByTaskIdAndUserId(long taskId, long userId);
}
