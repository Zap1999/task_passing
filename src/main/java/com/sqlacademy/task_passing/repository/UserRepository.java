package com.sqlacademy.task_passing.repository;

import com.sqlacademy.task_passing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
