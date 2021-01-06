package com.sqlacademy.task_passing.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
