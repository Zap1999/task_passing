package com.sqlacademy.task_passing.repository;

import com.sqlacademy.task_passing.entity.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
