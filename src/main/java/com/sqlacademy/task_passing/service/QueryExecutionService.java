package com.sqlacademy.task_passing.service;

import java.util.List;
import java.util.Map;

public interface QueryExecutionService {
    List<Map<String, Object>> executeQuery(String query);
}
