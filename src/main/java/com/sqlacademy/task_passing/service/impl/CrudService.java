package com.sqlacademy.task_passing.service.impl;

import com.sqlacademy.task_passing.entity.PassingSummary;
import com.sqlacademy.task_passing.entity.Role;
import com.sqlacademy.task_passing.entity.Task;
import com.sqlacademy.task_passing.entity.User;
import com.sqlacademy.task_passing.repository.PassingSummaryRepository;
import com.sqlacademy.task_passing.repository.RoleRepository;
import com.sqlacademy.task_passing.repository.TaskRepository;
import com.sqlacademy.task_passing.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrudService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PassingSummaryRepository passingSummaryRepository;


    public Role postRole(Role role) {
        return roleRepository.save(role);
    }

    public User postUser(User user) {
        return userRepository.save(user);
    }

    public Task postTask(Task task) {
        return taskRepository.save(task);
    }

    public PassingSummary postSummary(PassingSummary summary) {
        return passingSummaryRepository.save(summary);
    }

    public Role getRole(long id) {
        return roleRepository.findById(id).get();
    }

    public User getUser(long id) {
        return userRepository.findById(id).get();
    }

    public Task getTask(long id) {
        return taskRepository.findById(id).get();
    }

    public PassingSummary getSummary(long id) {
        return passingSummaryRepository.findById(id).get();
    }
}
