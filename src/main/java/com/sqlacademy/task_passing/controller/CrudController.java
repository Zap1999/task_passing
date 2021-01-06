package com.sqlacademy.task_passing.controller;

import com.sqlacademy.task_passing.entity.PassingSummary;
import com.sqlacademy.task_passing.entity.Role;
import com.sqlacademy.task_passing.entity.Task;
import com.sqlacademy.task_passing.entity.User;
import com.sqlacademy.task_passing.service.impl.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/crud")
public class CrudController {
    private final CrudService crudService;


    @GetMapping("/role")
    public ResponseEntity<Role> getRole(@RequestParam long id) {
        return ResponseEntity.ok(crudService.getRole(id));
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam long id) {
        return ResponseEntity.ok(crudService.getUser(id));
    }

    @GetMapping("/task")
    public ResponseEntity<Task> getTask(@RequestParam long id) {
        return ResponseEntity.ok(crudService.getTask(id));
    }

    @GetMapping("/summary")
    public ResponseEntity<PassingSummary> getSummary(@RequestParam long id) {
        return ResponseEntity.ok(crudService.getSummary(id));
    }

    @PostMapping("/role")
    public ResponseEntity<Role> postRole(@RequestBody Role role) {
        return ResponseEntity.ok(crudService.postRole(role));
    }

    @PostMapping("/user")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return ResponseEntity.ok(crudService.postUser(user));
    }

    @PostMapping("/task")
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        return ResponseEntity.ok(crudService.postTask(task));
    }

    @PostMapping("/summary")
    public ResponseEntity<PassingSummary> postSummary(@RequestBody PassingSummary summary) {
        return ResponseEntity.ok(crudService.postSummary(summary));
    }
}
