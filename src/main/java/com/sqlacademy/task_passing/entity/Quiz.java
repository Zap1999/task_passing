package com.sqlacademy.task_passing.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class Quiz {
    @Id
    @GeneratedValue
	private Long id;
    private String name;
}
