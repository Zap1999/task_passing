package com.sqlacademy.task_passing.service.impl;

import com.sqlacademy.task_passing.service.QueryExecutionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;


@Service
public class PostgreSqlQueryExecutionService implements QueryExecutionService {
    private final JdbcTemplate jdbcTemplate;
    @Value("classpath:sql/exec_db_init_script.sql")
    private final String initScript;


    public PostgreSqlQueryExecutionService(JdbcTemplate jdbcTemplate,
                                           @Value("classpath:sql/exec_db_init_script.sql") Resource resource
    ) throws IOException {
        this.jdbcTemplate = jdbcTemplate;
        this.initScript = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        initializeDatabase();
    }

    private void initializeDatabase() {
        jdbcTemplate.execute(initScript);
    }

    @Override
    public List<Map<String, Object>> executeQuery(String query) {
        return jdbcTemplate.queryForList(query);
    }
}
