package com.sqlacademy.task_passing.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Pattern;


@Getter
@AllArgsConstructor
public enum SqlQueryType {
    ALTER("ALTER", Pattern.compile("ALTER.*")),
    CREATE("CREATE", Pattern.compile("CREATE.*")),
    DROP("DROP", Pattern.compile("DROP.*")),
    RENAME("RENAME", Pattern.compile("RENAME.*")),
    DELETE("DELETE", Pattern.compile("DELETE.*")),
    INSERT("INSERT", Pattern.compile("INSERT.*")),
    SELECT("SELECT", Pattern.compile("SELECT.*")),
    UPDATE("UPDATE", Pattern.compile("UPDATE.*"));

    private final String startsWith;
    private final Pattern pattern;
}
