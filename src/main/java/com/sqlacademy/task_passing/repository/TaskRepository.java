package com.sqlacademy.task_passing.repository;

import com.sqlacademy.task_passing.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
