package com.sqlacademy.task_passing.repository;

import com.sqlacademy.task_passing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
